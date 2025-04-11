package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.company.CompanySimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.company.CompanyUpdateRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.*;
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

import java.util.List;
import java.util.Optional;

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
                throw new CompanyNotFoundException("You must be a company");
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

            return new EmployeeSimpleResponseDTO(employee.getId(), data.name(), employee.getCpf(), data.email());
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("employee_employee_cpf_key")) {
                throw new CpfAlreadyUsedException();
            } else if (errorMessage.contains("user_user_email_key")) {
                throw new EmailAlreadyUsedException();
            }
            throw e;
        }
    }

    @Transactional
    public EmployeeSimpleResponseDTO updateEmployee(EmployeeRequestDTO data){
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = (User)principal;

            Employee employee = employeeRepository.findByUser(user);

            if(employee==null){
                throw new EmployeeNotFoundException();
            }

            user.setEmail(data.email());
            employee.setName(data.name());
            employee.setCpf(data.cpf().replaceAll("\\D", ""));

            userRepository.save(user);
            employeeRepository.save(employee);

            return new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), user.getEmail());
        }catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("employee_employee_cpf_key")) {
                throw new CpfAlreadyUsedException();
            } else if (errorMessage.contains("user_user_email_key")) {
                throw new EmailAlreadyUsedException();
            }
            throw e;
        }
    }

    public EmployeeSimpleResponseDTO getEmployee(Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;

        if(id == null){
            Employee employee = employeeRepository.findByUser(user);

            if(employee == null){
                throw new EmployeeNotFoundException();
            }

            return new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), user.getEmail());
        }

        Company company = companyRepository.findByUser(user);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());

        if(employee.getCompany()!= company){
            throw new EmployeeNotFoundException("This employee isn't yours");
        }

        return new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail());
    }

    public List<EmployeeSimpleResponseDTO> getAllEmployees(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User)principal;

        Company company = companyRepository.findByUser(user);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        List<Employee> employees = employeeRepository.findAllByCompany(company);

        return employees.stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
    }
}
