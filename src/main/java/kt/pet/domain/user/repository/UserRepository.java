package kt.pet.domain.user.repository;


import kt.pet.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findByUserId(Long userSeq);
    Optional<User> findByEmail(String email);
    Optional<User> deleteUserById(Long userId);
    boolean existsByEmail(String email);
}