package kt.pet.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kt.pet.common.response.Response;
import kt.pet.domain.profile.dto.SignUpResponse;
import kt.pet.domain.user.dto.LoginRequest;
import kt.pet.domain.user.dto.SignInResponse;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @Operation(summary = "회원가입", description = "회원가입을 합니다")
    @PostMapping("/api/users/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public Response signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse signUpResponse = signService.signUp(request);
        return Response.success(signUpResponse);
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @PostMapping("/api/users/login")
    @ResponseStatus(HttpStatus.OK)
    public Response signIn(@Valid @RequestBody LoginRequest request) {
        SignInResponse signInResponse = signService.signIn(request);
        return Response.success(signInResponse);
    }

    //로그아웃
//    @PostMapping("/api/users/login")
//    @ResponseStatus(HttpStatus.OK)
//    public Response signIn(@Valid @RequestBody LoginRequest request) {
//        return Response.success();
//    }

    @Operation(summary = "회원탈퇴", description = "회원 탈퇴를 합니다.")
    @DeleteMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteUser(@PathVariable Long userId) {
        signService.deleteUser(userId);
        return Response.success();
    }


}
