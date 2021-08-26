package com.siemens.employee.service;


import com.siemens.employee.exception.CustomException;
import com.siemens.employee.mapper.EmployeeConverter;
import com.siemens.employee.model.dto.DeleteDTO;
import com.siemens.employee.model.dto.EmployeeDTO;
import com.siemens.employee.model.dto.ResponseDTO;
import com.siemens.employee.model.entity.AuthenticationEntity;
import com.siemens.employee.model.entity.EmployeeEntity;
import com.siemens.employee.repository.Authentication;
import com.siemens.employee.repository.Employee;
import com.siemens.employee.validators.EmployeeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeConverter employeeConverter;

    @Autowired
    EmployeeValidator employeeValidator;

    @Autowired
    Employee employee;

    @Autowired
    Authentication authentication;

    public ResponseDTO createEmployee(EmployeeDTO employeeDTO) throws CustomException {
        logger.info("the creation request came to service");
        employeeValidator.validateEmployee(employeeDTO);
        EmployeeEntity employeeEntity = employeeConverter.convertEmployeeDToEmployeeEntity(employeeDTO);
        AuthenticationEntity authenticationEntity = employeeConverter.convertEmployeeDTOtoAuthenticationEntity(employeeDTO);
        employee.save(employeeEntity);
        logger.info("employee details save into db");
        authentication.save(authenticationEntity);
        logger.info("authentication details saved into db");
        ResponseDTO responseDTO = new ResponseDTO(200,"Employee registration is successful","success");
        return responseDTO;
    }

    public ResponseDTO updateEmployeeData(EmployeeDTO employeeDTO) throws CustomException{
        logger.info("the update request came to service");
        employeeValidator.validateEmployeeId(employeeDTO);
        EmployeeEntity employeeEntity = employee.findByEmployeeId(Long.parseLong(employeeDTO.getId()));
        EmployeeEntity updatedEmployeeEntity = employeeConverter.updateEmployeeEntityWithEmployeeDTO(employeeDTO,employeeEntity);
        employee.save(updatedEmployeeEntity);
        logger.info("The employee details updated");
        ResponseDTO responseDTO = new ResponseDTO(200,"Employee details updated successfully","success");
        return responseDTO;
    }

    public ResponseDTO getAllEmployee(String employeeId) throws CustomException {
        if (employeeId == null){
            List<EmployeeDTO> employeeDTOList = new ArrayList<>();
            List<EmployeeEntity> employeeEntityList = employee.findAll();
            for (EmployeeEntity employeeEntity : employeeEntityList){
                EmployeeDTO employeeDTO = employeeConverter.employeeEntityToEmployeeDTO(employeeEntity);
                employeeDTOList.add(employeeDTO);
                ResponseDTO responseDTO = new ResponseDTO(200,"fetch all employee","success",employeeDTOList);
                return responseDTO;
            }
        }
        else {
            EmployeeEntity employeeEntity = employee.findByEmployeeId(Long.parseLong(employeeId));
            EmployeeDTO employeeDTO = employeeConverter.employeeEntityToEmployeeDTO(employeeEntity);
            ResponseDTO responseDTO = new ResponseDTO(200,"fetch employee","success",employeeDTO);
            return responseDTO;
        }
        throw new CustomException(400,"employee deatils not found","failed");
    }

    public ResponseDTO deleteEmployees(List<DeleteDTO> deleteDTOList){
        logger.info("deleting the employee details");
        for (DeleteDTO deleteDTO : deleteDTOList){
            logger.info("deleting id : {}",deleteDTO.getId());
            employee.deleteById(Long.parseLong(deleteDTO.getId()));
        }
        ResponseDTO responseDTO = new ResponseDTO(200,"Employee details deleted successfully","success");
        return responseDTO;
    }
}
