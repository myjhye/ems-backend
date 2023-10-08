package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    // 직원 정보 생성 - post
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        // employeeService 사용해 직원 생성 메소드 호출
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        // 생선된 직원 정보를 http 응답으로 반환 -> http 상태코드: 201 created
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    // 단일 직원 정보 조회 - get
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employeeDto);
    }


    // 전체 직원 정보 조회 - get
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees =  employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }


}
