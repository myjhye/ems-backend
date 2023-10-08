package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

// mapper -> 엔티티와 dto 간에 데이터를 변환하고 주고받음
public class EmployeeMapper {


    // 엔티티(Employee)를 DTO(EmployeeDto)로 변환
    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        return new EmployeeDto(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail()
        );
    }

    // DTO(EmployeeDto)를 엔티티(Employee)로 변환
    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        return new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail()
        );
    }

}

