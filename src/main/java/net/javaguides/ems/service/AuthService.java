package net.javaguides.ems.service;

import net.javaguides.ems.dto.JwtAuthResponse;
import net.javaguides.ems.dto.LoginDto;
import net.javaguides.ems.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    JwtAuthResponse login(LoginDto loginDto);
}
