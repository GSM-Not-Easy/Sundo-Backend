package noteasy.sundo.application.portfolio.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.portfolio.Portfolio;
import noteasy.sundo.queryfactory.portfolio.manager.PortfolioRepository;
import noteasy.sundo.queryfactory.student.Student;
import noteasy.sundo.queryfactory.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioSupportImpl implements PortfolioSupport {

    private final StudentRepository studentRepository;
    private final PortfolioRepository portfolioRepository;
    private final SecurityContextUtil securityContextUtil;

    @Override
    public void createPortfolio(PortfolioDto.CreatePortfolioRequest request) {
        User currentUser = securityContextUtil.currentUser();

        Student student = studentRepository.findByUser(currentUser)
                .orElseThrow(() -> new GlobalException("Student Not Found", HttpStatus.NOT_FOUND));


        if(portfolioRepository.existsByStudent(student)) {
            throw new GlobalException("Forbidden to create the Portfolio", HttpStatus.FORBIDDEN);
        }

        Portfolio portfolio = Portfolio.builder()
                .student(student)
                .introduce(request.getIntroduce())
                .githubLink(request.getGithubLink())
                .portfolioLink(request.getPortfolioLink())
                .blogLink(request.getBlogLink())
                .isDeleted(false)
                .build();

        portfolioRepository.save(portfolio);
    }

    @Override
    public PortfolioDto.Responses queryAllPortfolio(Integer grade, Integer classNum, String keyword) {
        List<Portfolio> portfolioList = portfolioRepository.search(grade, classNum, keyword);
        return PortfolioDto.listOf(portfolioList);
    }

    @Override
    public PortfolioDto.Detail queryPortfolioDetail(Long id) {
        Portfolio portfolio = portfolioRepository.queryById(id)
                .orElseThrow(() -> new GlobalException("Not Found Portfolio", HttpStatus.NOT_FOUND));

        return PortfolioDto.detailOf(portfolio);
    }

    @Override
    public void updatePortfolio(Long id, PortfolioDto.UpdatePortfolioRequest request) {
        Portfolio portfolio = portfolioRepository.queryById(id)
                .orElseThrow(() -> new GlobalException("Not Found Portfolio", HttpStatus.NOT_FOUND));

        validateToAccessPortfolio(portfolio);

        portfolioRepository.save(portfolio.updatePortfolio(request));
    }

    @Override
    public void deletePortfolio(Long id) {
        Portfolio portfolio = portfolioRepository.queryById(id)
                .orElseThrow(() -> new GlobalException("Not Found Portfolio", HttpStatus.NOT_FOUND));

        validateToAccessPortfolio(portfolio);

        portfolioRepository.deleteById(id);
    }

    private void validateToAccessPortfolio(Portfolio portfolio) {
        User currentUser = securityContextUtil.currentUser();

        Student student = studentRepository.findByUser(currentUser)
                .orElseThrow(() -> new GlobalException("Student Not Found", HttpStatus.NOT_FOUND));

        if(student != portfolio.getStudent()) {
            throw new GlobalException("Access Denied to access portfolio", HttpStatus.FORBIDDEN);
        }
    }

}
