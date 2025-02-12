package kt.pet.domain.user.controller;

import jakarta.validation.Valid;
import kt.pet.common.response.Response;
import kt.pet.domain.user.dto.LoginRequest;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    //회원가입
    @PostMapping("/api/users/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public Response signUp(@Valid @RequestBody SignUpRequest request) {
        signService.signUp(request);
        return Response.success();
    }

    //로그인
    @PostMapping("/api/users/login")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody LoginRequest request) {
        signService.signIn(request);
        return Response.success();
    }

    //로그아웃
//    @PostMapping("/api/users/login")
//    @ResponseStatus(HttpStatus.OK)
//    public Response signIn(@Valid @RequestBody LoginRequest request) {
//        return Response.success();
//    }

    //회원탈퇴
    @DeleteMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@PathVariable Long userId) {
        signService.deleteUser(userId);
        return Response.success();
    }


}
