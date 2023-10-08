package net.javaguides.ems.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// dto (data transfer object) -> 데이터 전송 형식 정의, 다른 시스템에 데이터 전송
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
