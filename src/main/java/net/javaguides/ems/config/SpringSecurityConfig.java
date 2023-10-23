package net.javaguides.ems.config;

import lombok.AllArgsConstructor;
import net.javaguides.ems.security.JwtAuthenticationEntryPoint;
import net.javaguides.ems.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;


    // 비밀번호 인코딩 -> 비밀번호 해시화
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    // 필터 체인 -> 유저 접속 권한 검사 -> 권한 없는 유저면 접속 차단
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {

                    // '/api/auth/**' 경로에 대한 모든 요청 허용 -> 인증 필요x
                    authorize.antMatchers("/api/auth/**").permitAll();
                    authorize.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                    // 나머지 모든 요청에 대해서는 인증 필요
                    authorize.anyRequest().authenticated();
                })

                // http basic 인증(사용자 인증 처리 방식) 활성화 -> 다른 인증 처리 방식 : oauth, jwt
                .httpBasic(Customizer.withDefaults());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));


        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }




    // 로그인 처리 -> 유저가 입력한 이름, 비밀번호 확인 -> 올바른 유저면 로그인 허용
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
