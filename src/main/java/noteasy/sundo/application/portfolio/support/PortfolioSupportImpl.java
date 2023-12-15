package noteasy.sundo.application.portfolio.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.portfolio.manager.PortfolioRepository;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentRepository;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PortfolioSupportImpl implements PortfolioSupport {

    private final StudentRepository studentPm;
    private final PortfolioRepository portfolioPm;
    private final SecurityContextUtil securityContextUtil;

    @Override
    public void createPortfolio(PortfolioDto.CreatePortfolioRequest request) {
        User currentUser = securityContextUtil.currentUser();

        Student student = studentPm.findByUser(currentUser)
                .orElseThrow(() -> new GlobalException("Student Not Found", HttpStatus.NOT_FOUND));


        if(portfolioPm.existsByStudent(student)) {
            throw new GlobalException("Forbidden to create the Portfolio", HttpStatus.FORBIDDEN);
        }

        Portfolio portfolio = Portfolio.builder()
                .student(student)
                .introduce(request.getIntroduce())
                .githubLink(request.getGithubLink())
                .portfolioLink(request.getPortfolioLink())
                .isDeleted(false)
                .build();

        portfolioPm.save(portfolio);
    }

    @Override
    public PortfolioDto.Responses queryAllPortfolio(Integer grade, Integer classNum, String keyword) {
        List<Portfolio> portfolioList = portfolioPm.search(grade, classNum, keyword);
        return PortfolioDto.listOf(portfolioList);
    }
}
