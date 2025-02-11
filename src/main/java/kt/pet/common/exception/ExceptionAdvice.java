package kt.pet.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kt.pet.common.response.Response;

@RestControllerAdvice
public class ExceptionAdvice {

	// @ExceptionHandler(UserNotFoundException.class)
	// @ResponseStatus(HttpStatus.NOT_FOUND) // 404
	// public Response memberNotFoundException() {
	// 	return Response.failure(-1001, "회원 정보를 찾을 수 없습니다.");
	// }
}
