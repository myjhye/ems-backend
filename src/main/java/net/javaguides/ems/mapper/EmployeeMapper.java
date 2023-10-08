package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;

// mapper -> 엔티티와 dto 간에 데이터를 변환하고 주고받음
public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {

        return new EmployeeDto(
             employee.getId(),
             employee.getFirstName(),
             employee.getLastName(),
             employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {

        return new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail()
        );
    }

}

