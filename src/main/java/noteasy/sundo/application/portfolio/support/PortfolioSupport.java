package noteasy.sundo.application.portfolio.support;

import noteasy.sundo.application.portfolio.dto.PortfolioDto;

public interface PortfolioSupport {
    void createPortfolio(PortfolioDto.CreatePortfolioRequest request);
    PortfolioDto.Responses queryAllPortfolio(Integer grade, Integer classNum, String keyword);
}
