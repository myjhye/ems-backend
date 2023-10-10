package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;


    // 부서 등록 rest api
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {

        // 부서 등록 메소드 호출
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);

        // 등록된 부서를 http 응답으로 반환
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

}
