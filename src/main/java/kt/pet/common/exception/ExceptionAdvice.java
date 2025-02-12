package kt.pet.common.exception;

import jakarta.el.MethodNotFoundException;
import kt.pet.domain.user.exception.LoginFailureException;
import kt.pet.domain.user.exception.UserDeleteFailureException;
import kt.pet.domain.user.exception.UserEmailAlreadyExistsException;
import kt.pet.domain.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kt.pet.common.response.Response;

@RestControllerAdvice
public class ExceptionAdvice {

	 @ExceptionHandler(UserEmailAlreadyExistsException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND) // 404
	 public Response userEmailAlreadyExistsException() {
	 	return Response.failure(-1001, "이미 존재하는 이메일입니다.");
	 }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Response memberNotFoundException() {
        return Response.failure(-1002, "사용자를 찾을 수 없습니다.");
    }

    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Response loginFailureException() {
        return Response.failure(-1003, "로그인에 실패했습니다.");
    }

    @ExceptionHandler(UserDeleteFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Response userDeleteFailureException() {
        return Response.failure(-1003, "로그인에 실패했습니다.");
    }



    @ExceptionHandler(MethodNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public Response methodNotFoundException(MethodArgumentNotValidException ex) {

        BindingResult bindingResult = ex.getBindingResult();

        //@Valid에서 발생하는 필드에러 메시지 추출
        String firstErrorMessage = bindingResult.getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("잘못된 요청입니다.");

        return Response.failure(-1001, firstErrorMessage);
    }
}
