package kt.pet.common.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// ✅ OncePerRequestFilter: 요청이 올 때마다 실행되는 필터
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey = "my-secret-key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // ✅ Header가 없는 경우 (토큰 미전송)
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 'Bearer '로 시작하지 않는 경우 (잘못된 토큰 형식)
        if (!authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 'Bearer ' 다음 문자열이 있는지 체크 후 추출
        String[] parts = authorizationHeader.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = parts[1];

        // ✅ 토큰이 유효한지 확인
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Jwt Token에서 이메일 추출
        String email = JwtTokenUtil.getEmail(token, secretKey);

        // ✅ 추출한 이메일로 User 찾아오기
        User loginUser = userService.findUserByEmail(email);

        // ✅ 이미 인증된 상태라면 그대로 필터 진행
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 인증 객체 생성 (권한이 없는 상태)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getEmail(), // ✅ 사용자 이메일 (Principal 역할)
                null,  // ✅ 비밀번호는 필요 없음
                null   // ✅ 권한 없음
        );

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // ✅ SecurityContext에 인증 정보 저장 → 컨트롤러에서 인증된 상태로 인식됨
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // ✅ 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
