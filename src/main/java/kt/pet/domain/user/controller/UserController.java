package kt.pet.domain.user.controller;

import jakarta.validation.Valid;
import kt.pet.common.response.Response;
import kt.pet.domain.user.dto.FoundUserResponse;
import kt.pet.domain.user.dto.PasswordResetRequest;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.dto.UserUpdateRequest;
import kt.pet.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원정보조회
    @GetMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response findUser(@PathVariable Long userId) {
        FoundUserResponse FoundUser = userService.findUser(userId);
        return Response.success(FoundUser);
    }

    //회원정보수정
    @PutMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long userId) {
        userService.updateUser(request,userId);
        return Response.success();
    }

    //비밀번호 리셋
    @PutMapping("/api/users/{userId}/password/reset")
    @ResponseStatus(HttpStatus.OK)
    public Response resetPassword(PasswordResetRequest request, @PathVariable Long userId) {
        userService.resetPassword(request,userId);
        return Response.success();
    }
}
