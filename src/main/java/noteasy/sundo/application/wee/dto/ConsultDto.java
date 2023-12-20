package noteasy.sundo.application.wee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ConsultDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateConsultRequest {

        @NotNull
        private LocalDateTime consultDate;

        @NotNull
        private Integer period;
    }
}
