package kt.pet.domain.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import kt.pet.common.response.Response;
import kt.pet.domain.profile.dto.PetProfileRegisterRequest;
import kt.pet.domain.profile.repository.PetRepository;
import kt.pet.domain.profile.service.PetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetProfileController {

    private final PetProfileService petProfileService;
    private final PetRepository petRepository;

    @Operation(summary = "펫 프로필 생성", description = "펫시터 프로필을 생성합니다.")
    @PostMapping("/api/users/{userId}/pet-profile")
    @ResponseStatus(HttpStatus.OK)
    public Response createPetProfile(@PathVariable Long userId, @Valid @RequestBody PetProfileRegisterRequest petProfileRegisterRequest) {
        petProfileService.createPetProfile(userId,petProfileRegisterRequest);
        return Response.success();
    }

    //조회
    @Operation(summary = "펫 프로필 목록 조회", description = "사용자의 펫 프로필 목록을 조회합니다.")
    @GetMapping("/api/users/{userId}/pet-profile")
    @ResponseStatus(HttpStatus.OK)
    public Response readPetProfile(@PathVariable Long userId) {
        return Response.success(petProfileService.readPetProfile(userId));
    }

    @Operation(summary = "펫 프로필 상세 조회", description = "사용자의 펫 프로필을 상세 조회합니다.")
    @GetMapping("/api/users/pet-profile/{petId}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOnePetProfile(@PathVariable Long petId) {
        System.out.println("readOnePetProfile");
        return Response.success(petProfileService.readOnePetProfile(petId));
    }

    @Operation(summary = "펫 프로필 수정", description = "사용자의 펫 프로필을 수정합니다.")
    @PutMapping("/api/users/pet-profile/{petId}")
    @ResponseStatus(HttpStatus.OK)
    public Response changePetProfile(@PathVariable Long petId, @Valid @RequestBody PetProfileRegisterRequest petProfileRegisterRequest) {
        System.out.println("changePetProfile");
        petProfileService.changePetProfile(petId,petProfileRegisterRequest);
        return Response.success();
    }

    @Operation(summary = "펫 프로필 삭제", description = "사용자의 펫 프로필을 삭제합니다.")
    @DeleteMapping("/api/users/pet-profile/{petId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePetProfile(@PathVariable Long petId) {
        System.out.println("deletePetProfile");
        petRepository.deleteById(petId);
        return Response.success();
    }

}
