package gr.aueb.cf.gymapp.services.exceptions;

public class RefreshTokenNotValidException extends RuntimeException {
    public static final Long serialVersionUID = 1L;

    public RefreshTokenNotValidException() {
        super("Refresh token expired");
    }
}
