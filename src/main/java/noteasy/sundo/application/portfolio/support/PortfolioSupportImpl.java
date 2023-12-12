package noteasy.sundo.application.portfolio.support;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.global.error.GlobalException;
import noteasy.sundo.global.library.security.SecurityContextUtil;
import noteasy.sundo.queryfactory.persistmodel.portfolio.Portfolio;
import noteasy.sundo.queryfactory.persistmodel.portfolio.manager.PortfolioPersistenceManager;
import noteasy.sundo.queryfactory.persistmodel.student.Student;
import noteasy.sundo.queryfactory.persistmodel.student.manager.StudentPersistenceManager;
import noteasy.sundo.queryfactory.persistmodel.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PortfolioSupportImpl implements PortfolioSupport {

    private final StudentPersistenceManager studentPm;
    private final PortfolioPersistenceManager portfolioPm;
    private final SecurityContextUtil securityContextUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
}
