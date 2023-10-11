package net.javaguides.ems.service;

import net.javaguides.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    // 부서 등록
    DepartmentDto createDepartment(DepartmentDto departmentDto);


    // 부서 조회 - 단일
    DepartmentDto getDepartmentById(Long departmentId);


    // 부서 조회 - 전체
    List<DepartmentDto> getAllDepartments();
}
