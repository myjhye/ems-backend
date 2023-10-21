package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.LoginDto;
import net.javaguides.ems.dto.RegisterDto;
import net.javaguides.ems.entity.Role;
import net.javaguides.ems.entity.User;
import net.javaguides.ems.exception.TodoAPIException;
import net.javaguides.ems.repository.RoleRepository;
import net.javaguides.ems.repository.UserRepository;
import net.javaguides.ems.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    // 회원가입
    @Override
    public String register(RegisterDto registerDto) {

        // username이 데이터베이스에 있는지 확인
        if (userRepository.existsByUsername(registerDto.getUsername())) {

            // 있으면 예외 던짐
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "유저 이름이 이미 있습니다");
        }


        // email이 데이터베이스에 있는지 확인
        if (userRepository.existsByEmail(registerDto.getEmail())) {

            // 있으면 예외 던짐
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "이메일이 이미 있습니다");
        }



        // 새 유저 생성
        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));



        // 유저에게 기본 역할 할당 -> ROLE_USER
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);


        // 유저 정보를 저장하고 데이터베이스에 등록
        userRepository.save(user);


        // 등록 성공 메세지 반환
        return "회원가입 성공";
    }





    // 로그인
    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "로그인 성공";
    }


}
