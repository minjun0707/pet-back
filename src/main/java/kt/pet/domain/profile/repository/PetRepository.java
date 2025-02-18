package kt.pet.domain.profile.repository;


import kt.pet.domain.profile.entity.PetProfile;
import kt.pet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepository extends JpaRepository<PetProfile, Long> {
    List<PetProfile> findByUserId(Long userId);
}
