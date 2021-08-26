package com.siemens.employee.configuration;


import com.siemens.employee.model.entity.AuthenticationEntity;
import com.siemens.employee.model.entity.EmployeeEntity;
import com.siemens.employee.repository.Authentication;
import com.siemens.employee.repository.Employee;
import com.siemens.employee.service.UtilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdminCreationScript {

    private static final Logger logger = LoggerFactory.getLogger(AdminCreationScript.class);


    @Value("${adminEmailId}")
    private String adminEmailId;

    @Value("${adminMobileNumber}")
    private String adminMobileNumber;

    @Autowired
    Employee employee;

    @Autowired
    Authentication authentication;

    @Autowired
    UtilityService utilityService;

    @PostConstruct
    public void createAdmin(){
        EmployeeEntity employeeEntityCheck = employee.findByEmailId(adminEmailId);
        if (employeeEntityCheck == null) {
            logger.info("the admin creation started");
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setCreatedBy("System");
            employeeEntity.setCreatedDate(System.currentTimeMillis());
            employeeEntity.setUpdatedDate(System.currentTimeMillis());
            employeeEntity.setEmailId(adminEmailId);
            employeeEntity.setMobileNumber(adminMobileNumber);
            employeeEntity.setFirstName("sahith");
            employeeEntity.setLastName("kumar");
            employeeEntity.setRole("admin");
            employeeEntity.setStatus("active");
            employeeEntity.setDepartment("high-authority");
            employeeEntity.setProjectName("-");
            employeeEntity.setDesignation("-");
            employee.save(employeeEntity);
            logger.info("the admin details saved");
            AuthenticationEntity authenticationEntity = new AuthenticationEntity();
            authenticationEntity.setUsername(adminEmailId);
            authenticationEntity.setPassword(utilityService.createRandomPassword());
            authenticationEntity.setStatus("active");
            authenticationEntity.setChange_password(false);
            authentication.save(authenticationEntity);
        }
        else {
            logger.info("======================== The admin already exist ==================");
        }
    }
}
