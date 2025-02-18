package kt.pet.domain.petSitter.service;

import kt.pet.domain.petSitter.dto.PetSitter;
import kt.pet.domain.petSitter.dto.PetSitterAllResponse;
import kt.pet.domain.petSitter.dto.PetSitterRegisterRequest;
import kt.pet.domain.petSitter.repository.PetSitterRepository;
import kt.pet.domain.profile.dto.PetProfileResponse;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetSitterService {

    private final PetSitterRepository petSitterRepository;
    private final UserRepository userRepository;


    @Transactional
    public void createPetSitter(Long userId, PetSitterRegisterRequest req) {

        User user = userRepository.findById(userId).orElseThrow();

        PetSitter petSitter = PetSitter.builder()
                .city(req.getCity())
                .availablePets(req.getAvailablePets())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .availableDays(req.getAvailableDays())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .hourlyRate(req.getHourlyRate())
                .user(user)
                .build();

        petSitterRepository.save(petSitter);
    }

    @Transactional
    public void changePetSitter(Long userId, PetSitterRegisterRequest req) {

        User user = userRepository.findById(userId).orElseThrow();

        PetSitter petSitter = PetSitter.builder()
                .city(req.getCity())
                .availablePets(req.getAvailablePets())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .availableDays(req.getAvailableDays())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .hourlyRate(req.getHourlyRate())
                .user(user)
                .build();
        petSitterRepository.save(petSitter);
    }

    @Transactional
    public void deletePetSitter(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        petSitterRepository.deletePetSitterByUser(user);
    }

    @Transactional
    public List<PetSitterAllResponse> readAllPetSitterProfile() {

        List<PetSitter> all = petSitterRepository.findAll();

        // 조회된 PetProfile 엔티티 리스트를 DTO 리스트로 변환
        return all.stream()
                .map(petSitter -> new PetSitterAllResponse(
                        petSitter.getId(),
                        petSitter.getCity(),
                        petSitter.getAvailablePets(),
                        petSitter.getStartDate(),
                        petSitter.getEndDate(),
                        petSitter.getAvailableDays(),
                        petSitter.getStartTime(),
                        petSitter.getEndTime(),
                        petSitter.getHourlyRate()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public PetSitterAllResponse readPetSitterProfile(Long userId) {

        PetSitter petSitter = petSitterRepository.findByUserId(userId);
        return PetSitterAllResponse.builder()
                .petSitterId(petSitter.getId())
                .city(petSitter.getCity())
                .availablePets(petSitter.getAvailablePets())
                .startDate(petSitter.getStartDate())
                .endDate(petSitter.getEndDate())
                .availableDays(petSitter.getAvailableDays())
                .startTime(petSitter.getStartTime())
                .endTime(petSitter.getEndTime())
                .hourlyRate(petSitter.getHourlyRate())
                .build();
    }


}
