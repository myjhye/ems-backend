package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // 직원 등록
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // 전달된 dto를 엔티티로 변환
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // 변환된 엔티티를 데이터베이스에 저장
        Employee savedEmployee = employeeRepository.save(employee);

        // 저장된 엔티티를 dto로 다시 변환하고 반환
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // 직원 조회 - 단일
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("해당 직원이 없습니다")
                );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    // 직원 조회 - 전체
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll(Sort.by(Sort.Order.desc("id")));

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    // 직원 수정
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository
                .findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("해당 직원이 없습니다"));

        employee.setFullName(updatedEmployee.getFullName());
        employee.setEmail(updatedEmployee.getEmail());

        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    // 직원 삭제
    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("해당 직원이 없습니다"));

        employeeRepository.deleteById(employeeId);
    }
}
