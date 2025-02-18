package kt.pet.domain.profile.service;

import kt.pet.domain.profile.dto.PetProfileRegisterRequest;

import kt.pet.domain.profile.dto.PetProfileResponse;
import kt.pet.domain.profile.entity.PetProfile;
import kt.pet.domain.profile.repository.PetRepository;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetProfileService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createPetProfile(Long userId, PetProfileRegisterRequest request) {
        User user = userRepository.findById(userId).orElseThrow();
        petRepository.save(PetProfileRegisterRequest.toEntity(user, request));
    }

    @Transactional
    public void changePetProfile(Long petId, PetProfileRegisterRequest request) {
        PetProfile petProfile = petRepository.findById(petId).orElseThrow();

        petProfile.setAge(request.getAge());
        petProfile.setType(request.getType());
        petProfile.setName(request.getName());

        System.out.println(request.getAge());
        System.out.println(request.getType());
        System.out.println(request.getName());
        petProfile.setImageUrl("https://flexible.img.hani.co.kr/flexible/normal/970/777/imgdb/resize/2019/0926/00501881_20190926.webp");

    }

    @Transactional(readOnly = true)
    public PetProfileResponse readOnePetProfile(Long petId) {
        PetProfile petProfile = petRepository.findById(petId).orElseThrow();
        PetProfileResponse response = new PetProfileResponse(petProfile.getId(), petProfile.getName(), "https://flexible.img.hani.co.kr/flexible/normal/970/777/imgdb/resize/2019/0926/00501881_20190926.webp", petProfile.getType(), petProfile.getAge());
        return response;
    }

    @Transactional(readOnly = true)
    public List<PetProfileResponse> readPetProfile(Long userId) {
        List<PetProfile> petProfileList = petRepository.findByUserId(userId);

        System.out.println(petProfileList.get(0).getName());

        // 조회된 PetProfile 엔티티 리스트를 DTO 리스트로 변환
        return petProfileList.stream()
                .map(pet -> new PetProfileResponse(
                        pet.getId(),
                        pet.getName(),
                        pet.getImageUrl(),
                        pet.getType(),
                        pet.getAge()
                ))
                .collect(Collectors.toList());
    }




}
