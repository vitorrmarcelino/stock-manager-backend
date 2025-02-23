package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.company.CompanyRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanySimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CnpjAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.EmailAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.PasswordsDoesntMatchException;
import com.vitorrmarcelino.stock_manager.model.Company;
import com.vitorrmarcelino.stock_manager.model.User;
import com.vitorrmarcelino.stock_manager.repository.CompanyRepository;
import com.vitorrmarcelino.stock_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CompanySimpleResponseDTO createCompany(CompanyRequestDTO data) {
        if (!data.password().equals(data.confirmpassword())) {
            throw new PasswordsDoesntMatchException();
        }
        try {
            User user = new User();
            user.setEmail(data.email());
            user.setPassword(data.password());
            user = userRepository.save(user);

            Company company = new Company();
            company.setCnpj(data.cnpj().replaceAll("\\D", ""));
            company.setName(data.name());
            company.setUser(user);
            companyRepository.save(company);

            return new CompanySimpleResponseDTO(data.name(), company.getCnpj(), data.email());
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("company_company_cnpj_key")) {
                throw new CnpjAlreadyUsedException();
            } else if (errorMessage.contains("user_user_email_key")) {
                throw new EmailAlreadyUsedException();
            }
            throw e;
        }
    }

}
