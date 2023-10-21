package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.LoginDto;
import net.javaguides.ems.dto.RegisterDto;
import net.javaguides.ems.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;


    // 회원가입 rest api

    // http post 요청 -> /api/auth/register 경로로 접근
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        // 화면에서 전송한 json 데이터를 RequestBody에 담아 서버에 보냄 -> 서버에서 받은 이 json 데이터를 RegisterDto 객체로 변환 -> 서버에서 읽을 수 있게


        // service (비즈니스 로직) 실행 -> 회원가입 처리
        String response = authService.register(registerDto);

        // 회원가입 성공 시 201 created 상태 & 결과 메세지 반환
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }




    // 로그인 rest api
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        String response = authService.login(loginDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
