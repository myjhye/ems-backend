package net.javaguides.ems.service;

import net.javaguides.ems.dto.DepartmentDto;

public interface DepartmentService {

    // 부서 등록
    DepartmentDto createDepartment(DepartmentDto departmentDto);


    // 부서 조회
    DepartmentDto getDepartmentById(Long departmentId);
}
