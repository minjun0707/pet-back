package kt.pet.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @Pattern(regexp = "^01[016789]\\d{7,8}$", message = "올바른 핸드폰 번호 형식이 아닙니다.")
    @NotBlank(message = "전화번호를 입력해주세요")
    private String phoneNumber;

}
