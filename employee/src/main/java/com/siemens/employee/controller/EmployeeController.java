package com.siemens.employee.controller;

import com.siemens.employee.exception.CustomException;
import com.siemens.employee.model.dto.DeleteDTO;
import com.siemens.employee.model.dto.EmployeeDTO;
import com.siemens.employee.model.dto.ResponseDTO;
import com.siemens.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            logger.info("The employee registration api called : {}",employeeDTO.toString());
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.createEmployee(employeeDTO));
        }catch (CustomException e){
            ResponseDTO responseDTO = new ResponseDTO(e.getCode(),e.getMessage(),e.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        catch (Exception e){
            logger.info("error in creating employee : {}",e.getMessage());
            ResponseDTO responseDTO = new ResponseDTO(500,"Employee creation failed","failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @PutMapping()
    public ResponseEntity<?> updateEmployeeData(@RequestBody EmployeeDTO employeeDTO){
        try{
            logger.info("The employee details edit api called");
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.updateEmployeeData(employeeDTO));
        }catch (CustomException e){
            ResponseDTO responseDTO = new ResponseDTO(e.getCode(),e.getMessage(),e.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        catch (Exception e){
            logger.info("error in employee updating : {}",e.getMessage());
            ResponseDTO responseDTO = new ResponseDTO(500,"Employee details updating failed","failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @GetMapping()
    public ResponseEntity<?> updateEmployeeData(@RequestParam(required = false) String id){
        try{
            logger.info("The employee details get api called");
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee(id));
        }catch (CustomException e){
            ResponseDTO responseDTO = new ResponseDTO(e.getCode(),e.getMessage(),e.getStatus());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        }
        catch (Exception e){
            logger.info("error in employee updating : {}",e.getMessage());
            ResponseDTO responseDTO = new ResponseDTO(500,"Employee details fetching failed","failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteEmployees(@RequestBody List<DeleteDTO> deleteDTOList){
        try{
            logger.info("the employee details deleting api called");
            return ResponseEntity.status(HttpStatus.OK).body(employeeService.deleteEmployees(deleteDTOList));
        }
        catch (Exception e){
            logger.info("error in employee updating : {}",e.getMessage());
            ResponseDTO responseDTO = new ResponseDTO(500,"Employee details fetching failed","failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }


}
