package kt.pet.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 권한 필요
//                        .anyRequest().authenticated()) // 나머지 요청은 인증 필요
                .httpBasic(httpBasic -> httpBasic.disable()) // 기본 HTTP 인증 비활성화
                .formLogin(form -> form.disable()); // 기본 로그인 폼 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 해싱 알고리즘 사용
    }
}
