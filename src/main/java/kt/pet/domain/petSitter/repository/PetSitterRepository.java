package kt.pet.domain.petSitter.repository;


import kt.pet.domain.petSitter.dto.PetSitter;
import kt.pet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter, Long> {
    PetSitter findByUserId(Long userId);
    void deleteByUser(User user); // ✅ User 엔티티 객체를 직접 받아서 삭제
    void deletePetSitterByUser(User user);
}


