package kt.pet.domain.user.service;

import kt.pet.domain.user.dto.LoginRequest;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.exception.LoginFailureException;
import kt.pet.domain.user.exception.UserEmailAlreadyExistsException;
import kt.pet.domain.user.exception.UserNotFoundException;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;


    public void signUp(SignUpRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        // 이메일, 닉네임 중복성 검사
        validateSignUpInfo(request);

        String subject = String.valueOf(user.getUserId());
        String accessToken = tokenService.createAccessToken(subject);
        String refreshToken = tokenService.createRefreshToken(subject);

        // 비밀번호 암호화
        String password = passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        // 회원 정보 저장
        userRepository.save(SignUpRequest.toEntity(request));

    }

    public void signIn(LoginRequest request) {

        //아이디 찾기
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        //비밀번호 대조
        if(validatePassword(request.getPassword(), user.getPassword())) {
            //로그인
        } else {
            throw new LoginFailureException();
        }

    }

    public void deleteUser(Long userId) {
        userRepository.deleteUserById(userId).orElseThrow(UserNotFoundException::new);
    }


    // 패스워드 동일성 검사
    private boolean validatePassword(String enterPassword, String userPassword) {
        return enterPassword.equals(userPassword);
    }

    // 이메일 중복성 검사
    private void validateSignUpInfo(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserEmailAlreadyExistsException(request.getEmail());
        }
    }
}
