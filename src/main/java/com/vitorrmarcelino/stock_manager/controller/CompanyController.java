package com.vitorrmarcelino.stock_manager.controller;

import com.vitorrmarcelino.stock_manager.dto.ErrorMessageResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanyRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanySimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanyUpdateRequestDTO;
import com.vitorrmarcelino.stock_manager.exception.EmailAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.PasswordsDoesntMatchException;
import com.vitorrmarcelino.stock_manager.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO){
            CompanySimpleResponseDTO res = companyService.createCompany(companyRequestDTO);
            return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity updateCompany(@Valid @RequestBody CompanyUpdateRequestDTO companyUpdateRequestDTO){
        CompanySimpleResponseDTO res = companyService.updateCompany(companyUpdateRequestDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("me")
    public ResponseEntity getOwnCompany(){
        CompanySimpleResponseDTO res = companyService.getCompany();
        return ResponseEntity.ok(res);
    }

}
