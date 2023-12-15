package noteasy.sundo.web.portfolio;

import lombok.RequiredArgsConstructor;
import noteasy.sundo.application.portfolio.dto.PortfolioDto;
import noteasy.sundo.application.portfolio.executor.PortfolioExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<PortfolioDto.Responses> queryAllPortfolioList(
            @RequestParam(defaultValue = "0") Integer grade,
            @RequestParam(defaultValue = "0") Integer number,
            @RequestParam(defaultValue = "") String keyword) {
        PortfolioDto.Responses responses = portfolioExecutor.queryAllPortfolioList(grade, number, keyword);
        return ResponseEntity.ok(responses);
    }
}
