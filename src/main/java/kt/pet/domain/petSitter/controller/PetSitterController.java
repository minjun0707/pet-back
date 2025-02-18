package kt.pet.domain.petSitter.controller;

import io.swagger.v3.oas.annotations.Operation;
import kt.pet.common.response.Response;
import kt.pet.domain.petSitter.dto.PetSitterRegisterRequest;
import kt.pet.domain.petSitter.service.PetSitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PetSitterController {

    private final PetSitterService petSitterService;


    @Operation(summary = "펫시터 프로필 생성", description = "펫시터 프로필을 생성합니다.")
    @PostMapping("/api/users/pet-sitter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response createPetSitter(@PathVariable Long userId, @RequestBody PetSitterRegisterRequest req) {
        System.out.println("create");
        petSitterService.createPetSitter(userId, req);
        return Response.success();
    }

    @Operation(summary = "펫시터 프로필 수정", description = "펫시터 프로필을 수정합니다.")
    @PutMapping("/api/users/pet-sitter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response changePetSitter(@PathVariable Long userId, @RequestBody PetSitterRegisterRequest req) {
        System.out.println("changePetSitter");
        petSitterService.changePetSitter(userId, req);
        return Response.success();
    }

    @Operation(summary = "펫시터 프로필 삭제", description = "펫시터 프로필을 삭제합니다")
    @DeleteMapping("/api/users/pet-sitter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response deletePetSitter(@PathVariable Long userId) {
        System.out.println("deletePetSitter");
        petSitterService.deletePetSitter(userId);
        return Response.success();
    }

    @Operation(summary = "펫시터 목록 조회", description = "펫시터 목록을 조회합니다.")
    @GetMapping("/api/users/pet-sitter")
    @ResponseStatus(HttpStatus.OK)
    public Response readAllPetSitterProfile() {
        System.out.println("readAllPetSitterProfile");
        return Response.success(petSitterService.readAllPetSitterProfile());
    }

    @Operation(summary = "펫시터 상세 조회", description = "펫시터를 상제 조회합니다.")
    @GetMapping("/api/users/pet-sitter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Response readPetSitterProfile(@PathVariable Long userId) {

        System.out.println("petSitter profile read");
        return Response.success(petSitterService.readPetSitterProfile(userId));
    }




//
//    //수정
//    @PutMapping("/api/users/pet-profile/{petId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response changePetProfile(@PathVariable Long petId, @Valid @RequestBody PetProfileRegisterRequest petProfileRegisterRequest) {
//        System.out.println("changePetProfile");
//        petProfileService.changePetProfile(petId,petProfileRegisterRequest);
//        return Response.success();
//    }
//
//    //수정
//    @DeleteMapping("/api/users/pet-profile/{petId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Response deletePetProfile(@PathVariable Long petId) {
//        System.out.println("deletePetProfile");
//        petRepository.deleteById(petId);
//        return Response.success();
//    }

}
