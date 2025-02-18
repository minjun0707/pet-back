package kt.pet.domain.profile.dto;

import kt.pet.domain.profile.entity.PetProfile;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetProfileRegisterRequest {
    private String name;
    private String age;
    private String imageUrl;
    private String type;

    public static PetProfile toEntity(User user,PetProfileRegisterRequest request) {
        return new PetProfile(request.getName(), request.getAge(), request.getType(), "https://flexible.img.hani.co.kr/flexible/normal/970/777/imgdb/resize/2019/0926/00501881_20190926.webp", user);
    }
}
