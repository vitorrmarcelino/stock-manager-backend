package com.vitorrmarcelino.stock_manager.controller;

import com.vitorrmarcelino.stock_manager.dto.company.CompanyRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanySimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity createEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        EmployeeSimpleResponseDTO res =  employeeService.createEmployee(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        EmployeeSimpleResponseDTO res = employeeService.updateEmployee(employeeRequestDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Integer id){
        EmployeeSimpleResponseDTO res = employeeService.getEmployee(id);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity getEmployee(){
        Integer id = null;
        EmployeeSimpleResponseDTO res = employeeService.getEmployee(id);
        return ResponseEntity.ok(res);
    }

}
