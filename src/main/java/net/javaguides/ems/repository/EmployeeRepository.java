package net.javaguides.ems.repository;

import net.javaguides.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// jpa repository를 사용해 employee 엔티티와 관련된 데이터베이스 엑세스 (CRUD) 수행
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
