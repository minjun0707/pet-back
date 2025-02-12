package kt.pet.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoundUserResponse {
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
}
