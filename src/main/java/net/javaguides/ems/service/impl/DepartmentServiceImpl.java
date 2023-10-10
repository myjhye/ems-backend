package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        // dto -> entity 변환
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        // 변환된 entity를 db에 저장
        Department savedDepartment = departmentRepository.save(department);

        // 저장된 entity -> dto 변환하고 반환
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }
}
