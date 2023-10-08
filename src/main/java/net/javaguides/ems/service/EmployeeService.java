package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;


public interface EmployeeService {

    // 직원 정보 생성 메소드
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
