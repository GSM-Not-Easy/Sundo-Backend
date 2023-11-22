package noteasy.sundo.global.error.response;

public record ErrorResponse(String message) {

    public ErrorResponse of(final Throwable cause) {
        return new ErrorResponse(cause.getMessage());
    }
}
