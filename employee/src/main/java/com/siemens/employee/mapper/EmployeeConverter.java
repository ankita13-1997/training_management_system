package com.siemens.employee.mapper;

import com.siemens.employee.model.dto.EmployeeDTO;
import com.siemens.employee.model.entity.AuthenticationEntity;
import com.siemens.employee.model.entity.EmployeeEntity;
import com.siemens.employee.service.UtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeConverter.class);

    @Autowired
    UtilityService utilityService;

    public EmployeeEntity convertEmployeeDToEmployeeEntity(EmployeeDTO employeeDTO){
        logger.info("the convert employee dto to employee db model");
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDesignation(employeeDTO.getDesignation());
        employeeEntity.setProjectName(employeeDTO.getProject());
        employeeEntity.setStatus("active");
        employeeEntity.setRole(employeeDTO.getRole());
        employeeEntity.setDepartment(employeeDTO.getDepartment());
        employeeEntity.setCreatedBy(employeeDTO.getCreatedBy());
        employeeEntity.setFirstName(employeeDTO.getFirstName());
        employeeEntity.setLastName(employeeDTO.getLastName());
        employeeEntity.setEmailId(employeeDTO.getEmailId());
        employeeEntity.setMobileNumber(employeeDTO.getMobileNumber());
        employeeEntity.setCreatedDate(System.currentTimeMillis());
        employeeEntity.setUpdatedDate(System.currentTimeMillis());
        return employeeEntity;
    }

    public EmployeeEntity updateEmployeeEntityWithEmployeeDTO(EmployeeDTO employeeDTO,EmployeeEntity employeeEntity){
        logger.info("the employee detail updating");
        if (employeeDTO.getDepartment() != null || employeeDTO.getDepartment() != ""){
            if (!employeeDTO.getDepartment().equals(employeeEntity.getDepartment())){
                 employeeEntity.setDepartment(employeeDTO.getDepartment());
                 logger.info("the employee department changed");
            }
        }

        if (employeeDTO.getDesignation() != null || employeeDTO.getDesignation()!= "") {
            if (!employeeDTO.getDesignation().equals(employeeEntity.getDepartment())) {
                employeeEntity.setDesignation(employeeDTO.getDesignation());
                logger.info("the employee designation changed");
            }
        }

        if (employeeDTO.getMobileNumber() != null || employeeDTO.getMobileNumber() != "") {
            if (!employeeDTO.getMobileNumber().equals(employeeEntity.getMobileNumber())) {
                employeeEntity.setMobileNumber(employeeDTO.getMobileNumber());
                logger.info("the employee mobile number changed");
            }
        }

        if (employeeDTO.getStatus() != null || employeeDTO.getStatus() != "") {
            if (!employeeDTO.getStatus().equals(employeeEntity.getStatus())) {
                employeeEntity.setStatus(employeeDTO.getStatus());
                logger.info("the employee status changed");
            }
        }

        if (employeeDTO.getFirstName() != null || employeeDTO.getFirstName() != "") {
            if (!employeeDTO.getFirstName().equals(employeeEntity.getFirstName())) {
                employeeEntity.setFirstName(employeeDTO.getFirstName());
                logger.info("the employee first name changed");
            }
        }

        if (employeeDTO.getLastName() != null || employeeDTO.getLastName()!= "") {
            if (!employeeDTO.getLastName().equals(employeeEntity.getLastName())) {
                employeeEntity.setLastName(employeeDTO.getLastName());
                logger.info("the employee last name changed");
            }
        }
        if (employeeDTO.getRole() == null || employeeDTO.getRole() != "") {
            if (!employeeDTO.getRole().equals(employeeEntity.getRole())) {
                employeeEntity.setRole(employeeDTO.getRole());
            }
        }
        return employeeEntity;
    }

    public AuthenticationEntity convertEmployeeDTOtoAuthenticationEntity(EmployeeDTO employeeDTO){
        logger.info("the conversion of employee dto to authentication db model");
        AuthenticationEntity authenticationEntity = new AuthenticationEntity();
        authenticationEntity.setPassword(utilityService.createRandomPassword());
        authenticationEntity.setUsername(employeeDTO.getEmailId());
        authenticationEntity.setStatus("active");
        authenticationEntity.setChange_password(false);
        return authenticationEntity;
    }


    public EmployeeDTO employeeEntityToEmployeeDTO(EmployeeEntity employeeEntity){
        logger.info("The employee entity to employee dto conversion");
        EmployeeDTO employeeDTO =  new EmployeeDTO();
        employeeDTO.setId(String.valueOf(employeeEntity.getEmployeeId()));
        employeeDTO.setCreatedBy(employeeEntity.getCreatedBy());
        employeeDTO.setDepartment(employeeEntity.getDepartment());
        employeeDTO.setDesignation(employeeEntity.getDesignation());
        employeeDTO.setEmailId(employeeEntity.getEmailId());
        employeeDTO.setMobileNumber(employeeEntity.getMobileNumber());
        employeeDTO.setFirstName(employeeEntity.getFirstName());
        employeeDTO.setLastName(employeeEntity.getLastName());
        employeeDTO.setProject(employeeEntity.getProjectName());
        employeeDTO.setRole(employeeEntity.getRole());
        employeeDTO.setStatus(employeeEntity.getStatus());
        employeeDTO.setCreatedDate(utilityService.epochToDateTime(employeeEntity.getCreatedDate()));
        employeeDTO.setUpdatedDate(utilityService.epochToDateTime(employeeEntity.getUpdatedDate()));
        return employeeDTO;
    }


}
