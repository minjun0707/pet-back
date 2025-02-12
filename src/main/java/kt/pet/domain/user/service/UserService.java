package kt.pet.domain.user.service;

import kt.pet.domain.user.dto.FoundUserResponse;
import kt.pet.domain.user.dto.PasswordResetRequest;
import kt.pet.domain.user.dto.UserUpdateRequest;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.exception.UserNotFoundException;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public FoundUserResponse findUser(Long userId) {

        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return new FoundUserResponse(user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
    }


    public void updateUser(UserUpdateRequest request,Long userId) {

        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        //회원 정보 수정
        user.updateUserInfo(request.getEmail(), request.getPassword(), request.getName(), request.getPhoneNumber());
    }

    public void resetPassword(PasswordResetRequest request, Long userId) {

        //회원 조회
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        //비밀번호 암호화 로직 추가

        //비밀번호 변경
        user.setPassword(request.getPassword());
    }


}
