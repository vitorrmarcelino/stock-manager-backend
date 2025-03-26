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

    @Operation(description = "Endpoint responsible for creating new employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Unnauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error"),
    })
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

}
