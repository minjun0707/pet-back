package kt.pet.domain.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PetProfileResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String type;
    private String age;
}
