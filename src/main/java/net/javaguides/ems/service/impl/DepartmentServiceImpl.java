package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.DepartmentDto;
import net.javaguides.ems.entity.Department;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.DepartmentMapper;
import net.javaguides.ems.repository.DepartmentRepository;
import net.javaguides.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;


    // 부서 등록
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        // dto -> entity 변환
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        // 변환된 entity를 db에 저장
        Department savedDepartment = departmentRepository.save(department);

        // 저장된 entity -> dto 변환하고 반환
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }



    // 부서 조회 - 단일
    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(
                     () -> new ResourceNotFoundException(departmentId + " - 해당 부서가 없습니다" )
                );

        return DepartmentMapper.mapToDepartmentDto(department);
    }



    // 부서 조회 - 전체
    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department))
                .collect(Collectors.toList());
    }





}
