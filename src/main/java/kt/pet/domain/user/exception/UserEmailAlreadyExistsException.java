package kt.pet.domain.user.exception;


public class UserEmailAlreadyExistsException extends RuntimeException {
    public UserEmailAlreadyExistsException(String message) {
        super(message);
    }
}