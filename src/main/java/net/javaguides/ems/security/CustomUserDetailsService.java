package net.javaguides.ems.security;

import lombok.AllArgsConstructor;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;


    // spring security의 UserDetailService 인터페이스(유저 정보 검색 & 인증 인터페이스)를 구현한 메소드
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // username 또는 email로 사용자 정보를 데이터베이스에서 조회
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("해당 이름과 이메일에 일치하는 유저가 없습니다"));



        // 사용자 권한을 가져와서 spring security의 GrantedAuthority 객체로 변환해 저장
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());



        // spring security에서 유저 인증, 권한 부여에 활용됨
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail, // 유저 이름 또는 이메일
                null, // 비밀번호 (여기서는 사용x)
                authorities // 사용자의 권한 정보
        );
    }
}
