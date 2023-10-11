package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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



    // 부서 조회 rest api - 단일
    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId) {

        // 부서 조회 메소드 호출
        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(departmentDto);
    }



    // 부서 조회 rest api - 전체
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }



    // 부서 수정 rest api
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updatedDepartment(@PathVariable("id") Long departmentId,
                                                           @RequestBody DepartmentDto departmentDto) {

        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentId, departmentDto);

        return ResponseEntity.ok(departmentDto);
    }


    // 부서 삭제 rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartment((departmentId));

        return ResponseEntity.ok("해당 직원 정보가 삭제 되었습니다.");
    }

}
