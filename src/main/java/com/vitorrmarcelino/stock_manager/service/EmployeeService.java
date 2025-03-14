package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.company.CompanySimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CnpjAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.EmailAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.PasswordsDoesntMatchException;
import com.vitorrmarcelino.stock_manager.model.Company;
import com.vitorrmarcelino.stock_manager.model.Employee;
import com.vitorrmarcelino.stock_manager.model.User;
import com.vitorrmarcelino.stock_manager.repository.CompanyRepository;
import com.vitorrmarcelino.stock_manager.repository.EmployeeRepository;
import com.vitorrmarcelino.stock_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public EmployeeSimpleResponseDTO createEmployee(EmployeeRequestDTO data){
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User userCompany = (User)principal;

            Company company = companyRepository.findByUser((User) userCompany);
            if(company == null){
                throw new PasswordsDoesntMatchException("teste");
            }
            User user = new User();
            user.setEmail(data.email());
            user.setPassword(new BCryptPasswordEncoder().encode(data.email()));
            user.setIsSuper(false);
            user = userRepository.save(user);

            Employee employee = new Employee();
            employee.setCpf(data.cpf().replaceAll("\\D", ""));
            employee.setName(data.name());
            employee.setUser(user);
            employee.setCompany(company);
            employeeRepository.save(employee);

            return new EmployeeSimpleResponseDTO(data.name(), employee.getCpf(), data.email());
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
