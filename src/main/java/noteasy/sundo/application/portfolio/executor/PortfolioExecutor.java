package noteasy.sundo.application.portfolio.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.application.portfolio.support.PortfolioSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PortfolioExecutor {

    private final PortfolioSupport portfolioSupport;

    @Transactional(rollbackFor = Exception.class)
    public void executeCreatePortfolio(PortfolioDto.CreatePortfolioRequest request) {
        portfolioSupport.createPortfolio(request);
    }

    public PortfolioDto.Responses executeQueryAllPortfolioList(Integer grade, Integer classNum, String keyword) {
        return portfolioSupport.queryAllPortfolio(grade, classNum, keyword);
    }

    public PortfolioDto.Detail executeQueryPortfolioDetail(Long id) {
        return portfolioSupport.queryPortfolioDetail(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeUpdatePortfolio(Long id, PortfolioDto.UpdatePortfolioRequest request) {
        portfolioSupport.updatePortfolio(id, request);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeDeletePortfolio(Long id) {
        portfolioSupport.deletePortfolio(id);
    }
}
