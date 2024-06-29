package gr.aueb.cf.gymapp.services.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public UserNotFoundException(String username) {
        super("User with username " + username + " was not found");
    }

    public UserNotFoundException(Long id) {
        super("User with id " + id + " was not found");
    }
}
