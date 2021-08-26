package com.siemens.employee.validators;

import com.siemens.employee.exception.CustomException;
import com.siemens.employee.model.dto.EmployeeDTO;
import com.siemens.employee.model.entity.EmployeeEntity;
import com.siemens.employee.repository.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeValidator {

    @Autowired
    Employee employee;

    public void validateEmployee(EmployeeDTO employeeDTO) throws CustomException {
        EmployeeEntity employeeEntity = employee.findByEmailId(employeeDTO.getEmailId());
        if (employeeEntity != null){
            throw new CustomException(400,"The email id already exist","failed");
        }
    }

    public void validateEmployeeId(EmployeeDTO employeeDTO) throws CustomException{
        if (employeeDTO.getId() == null){
            throw new CustomException(400,"Employee io not found","failed");
        }
        EmployeeEntity employeeEntity = employee.findByEmployeeId(Long.parseLong(employeeDTO.getId()));
        if (employeeEntity == null){
            throw new CustomException(400,"Employee io not found","failed");
        }
    }

}
