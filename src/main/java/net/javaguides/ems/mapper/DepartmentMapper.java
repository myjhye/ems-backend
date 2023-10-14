package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;

public class DepartmentMapper {

    // entity -> dto 변환
    public static DepartmentDto mapToDepartmentDto(Department department) {

        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    // dto -> entity 변환
    public static Department mapToDepartment(DepartmentDto departmentDto) {

        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()

        );
    }
}
