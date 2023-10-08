package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {

    // 직원 정보 생성
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    // 직원 정보 조회 -> 단일
    EmployeeDto getEmployeeById(Long employeeId);

    // 직원 정보 조회 -> 전체
    List<EmployeeDto> getAllEmployees();

    // 직원 정보 수정
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
}
