package kt.pet.domain.user.service;

import kt.pet.domain.user.dto.FoundUserResponse;
import kt.pet.domain.user.dto.PasswordResetRequest;
import kt.pet.domain.user.dto.UserUpdateRequest;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.exception.UserNotFoundException;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public FoundUserResponse findUser(Long userId) {

        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return new FoundUserResponse(user.getId(),user.getName(),user.getEmail(),user.getPhoneNumber());
    }


    public User findUserByEmail(String email) {

        //회원 조회
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        return user;
    }


    @Transactional
    public void updateUser(UserUpdateRequest request,Long userId) {

        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        //회원 정보 수정
        user.updateUserInfo(request.getEmail(), request.getName(), request.getPhoneNumber());
    }


    @Transactional
    public void changePassword(PasswordResetRequest request, Long userId) {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        System.out.println(request.getPassword());
        String password = passwordEncoder.encode(request.getPassword());
        user.setPassword(password);
    }


}
