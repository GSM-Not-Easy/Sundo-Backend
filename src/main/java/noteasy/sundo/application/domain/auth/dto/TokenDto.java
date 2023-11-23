package noteasy.sundo.application.domain.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class TokenDto {

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        public final String accessToken;
        public final String refreshToken;
        public final LocalDateTime accessExp;
        public final LocalDateTime refreshExp;
    }
}
