package com.siemens.employee.repository;

import com.siemens.employee.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Employee extends JpaRepository<EmployeeEntity,Long> {

    EmployeeEntity findByEmailId(String emailId);

    EmployeeEntity findByEmployeeId(Long employeeId);
}
