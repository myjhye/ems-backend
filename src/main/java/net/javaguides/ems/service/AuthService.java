package net.javaguides.ems.service;

import net.javaguides.ems.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
}
