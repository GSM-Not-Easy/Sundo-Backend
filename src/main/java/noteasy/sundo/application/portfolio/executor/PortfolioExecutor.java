package noteasy.sundo.application.portfolio.executor;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.application.portfolio.support.PortfolioSupport;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioExecutor {

    private final PortfolioSupport portfolioSupport;

    public void executeCreatePortfolio(PortfolioDto.CreatePortfolioRequest request) {
        portfolioSupport.createPortfolio(request);
    }
}
