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

@Entity
@Table(name = "employees", schema = "ems")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom-id-generator")
    @SequenceGenerator(name = "custom-id-generator", sequenceName = "custom_sequence", initialValue = 1000)
    private Long id;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "department")
    private String department;

}
