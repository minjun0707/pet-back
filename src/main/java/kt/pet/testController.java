package kt.pet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kt.pet.common.response.Response;

@RestController
public class testController {

	@GetMapping("/api/test")
	@ResponseStatus(HttpStatus.OK)
	public Response test() {
		return Response.success();
	}

}