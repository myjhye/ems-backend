package net.javaguides.ems.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// jpa 엔티티 클래스임을 나타냄
@Entity

// 엔티티와 데이터베이스 테이블 간의 매핑정보 지정
@Table(name = "employees", schema = "ems")



// entity -> db 테이블과 매핑 & 데이터를 저장, 관리
public class Employee {

    // 엔티티의 primary key인 필드
    @Id
    // primary key 값 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // 엔티티 필드와 데이터베이스 컬럼 간의 매핑 정보 지정
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;
}
