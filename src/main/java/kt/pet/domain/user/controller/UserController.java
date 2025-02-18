package kt.pet.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "사용자 조회", description = "사용자를 조회합니다.")
    @GetMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response findUser(@PathVariable Long userId) {
        FoundUserResponse FoundUser = userService.findUser(userId);
        return Response.success(FoundUser);
    }

    @Operation(summary = "사용자 수정", description = "사용자 정보를 수정합니다")
    @PutMapping("/api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateUser(@Valid @RequestBody UserUpdateRequest request, @PathVariable Long userId) {
        userService.updateUser(request,userId);
        return Response.success();
    }

    @Operation(summary = "비밀번호 변경", description = "사용자의 비밀번호를 변경합니다.")
    @PutMapping("/api/users/password/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response changePassword(@RequestBody PasswordResetRequest request, @PathVariable Long userId) {
        userService.changePassword(request,userId);
        return Response.success();
    }
}
