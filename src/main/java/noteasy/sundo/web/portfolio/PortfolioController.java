package noteasy.sundo.web.portfolio;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.application.portfolio.executor.PortfolioExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioExecutor portfolioExecutor;

    @PostMapping
    public ResponseEntity<Void> createPortfolio(PortfolioDto.CreatePortfolioRequest request) {
        portfolioExecutor.executeCreatePortfolio(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PortfolioDto.Responses> queryAllPortfolioList() {
        PortfolioDto.Responses responses = portfolioExecutor.queryAllPortfolioList();
        return ResponseEntity.ok(responses);
    }
}
