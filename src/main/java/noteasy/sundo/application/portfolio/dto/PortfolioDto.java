package noteasy.sundo.application.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import reactor.util.annotation.Nullable;

import javax.validation.constraints.NotBlank;

public class PortfolioDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatePortfolioRequest {

        @NotBlank
        private String introduce;

        @Nullable
        private String githubLink;

        @Nullable
        private String portfolioLink;
    }
}
