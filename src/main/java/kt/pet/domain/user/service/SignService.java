package kt.pet.domain.user.service;

import kt.pet.common.jwt.JwtTokenUtil;
import kt.pet.domain.profile.dto.SignUpResponse;
import kt.pet.domain.user.dto.LoginRequest;
import kt.pet.domain.user.dto.SignInResponse;
import kt.pet.domain.user.dto.SignUpRequest;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.exception.LoginFailureException;
import kt.pet.domain.user.exception.UserEmailAlreadyExistsException;
import kt.pet.domain.user.exception.UserNotFoundException;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public SignUpResponse signUp(SignUpRequest request) {

        // 이메일, 닉네임 중복성 검사
        validateSignUpInfo(request);

        // 비밀번호 암호화
        String password = passwordEncoder.encode(request.getPassword());
        request.setPassword(password);

        // 회원 정보 저장
        User user = userRepository.save(SignUpRequest.toEntity(request));

        return new SignUpResponse(user.getId(),JwtTokenUtil.createToken(user.getEmail()));
    }

    public SignInResponse signIn(LoginRequest request) {

        //아이디 찾기
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        //비밀번호 대조
        validatePassword(request.getPassword(), user.getPassword());

        String accessToken = JwtTokenUtil.createToken(user.getEmail());

        System.out.println(accessToken);

        return new SignInResponse(user.getId(),user.getName(),accessToken); // 5
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteUserById(userId).orElseThrow(UserNotFoundException::new);
    }


    // 패스워드 동일성 검사
    private void validatePassword(String enterPassword, String userPassword) {
        if (!passwordEncoder.matches(enterPassword, userPassword)) {
            throw new LoginFailureException();
        }
    }

    // 이메일 중복성 검사
    private void validateSignUpInfo(SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserEmailAlreadyExistsException(request.getEmail());
        }
    }
}
