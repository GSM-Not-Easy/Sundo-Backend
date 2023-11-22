package noteasy.sundo.global.error.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String message;

    public static ErrorResponse of(final Throwable cause) {
        return new ErrorResponse(cause.getMessage());
    }
}
