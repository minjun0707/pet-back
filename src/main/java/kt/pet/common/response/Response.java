package kt.pet.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
//null 값을 가지는 필드는, JSON 응답에 포함되지 않도록
@AllArgsConstructor
@Getter
//응답 객체를 JSON으로 변환

public class Response {
	private boolean status;
	private int code;
	private Result result;

	public static Response success() { // 4
		return new Response(true, 200, null);
	}

	//응답 데이터 추가 반환
	public static <T> Response success(T data) { // 5
		return new Response(true, 200, new Success<>(data));
	}

	//실패 메시지도 반환
	public static Response failure(int code, String msg) { // 6
		return new Response(false, code, new Failure(msg));
	}
}