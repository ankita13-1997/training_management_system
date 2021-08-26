package com.siemens.employee.repository;

import com.siemens.employee.model.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Authentication extends JpaRepository<AuthenticationEntity,String> {
}
