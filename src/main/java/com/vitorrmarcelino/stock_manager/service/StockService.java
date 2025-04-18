package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CompanyNotFoundException;
import com.vitorrmarcelino.stock_manager.exception.StockNotFoundException;
import com.vitorrmarcelino.stock_manager.model.Company;
import com.vitorrmarcelino.stock_manager.model.Stock;
import com.vitorrmarcelino.stock_manager.model.User;
import com.vitorrmarcelino.stock_manager.repository.CompanyRepository;
import com.vitorrmarcelino.stock_manager.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    CompanyRepository companyRepository;

    public StockResponseDTO createStock(StockRequestDTO data){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Stock stock = new Stock();
        stock.setName(data.name());
        stock.setCompany(company);

        stockRepository.save(stock);

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(stock.getEmployeesWithAccess()!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees);
    }

    public StockResponseDTO updateStock(StockRequestDTO data, Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException());

        if(!stock.getCompany().getId().equals(company.getId())){
            throw new StockNotFoundException();
        }

        stock.setName(data.name());
        stock.setCompany(company);

        stockRepository.save(stock);

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(stock.getEmployeesWithAccess()!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees);
    }

    public void deleteStock(Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException());

        if(!stock.getCompany().getId().equals(company.getId())){
            throw new StockNotFoundException();
        }

        stockRepository.delete(stock);

    }

    public List<StockResponseDTO> getAllStocks(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        List<Stock> stocks = stockRepository.findAllByCompany(company);

        return stocks.stream().map(stock -> {
            List<EmployeeSimpleResponseDTO> listOfEmployees;

            if(stock.getEmployeesWithAccess()!=null){
                listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
            } else {
                listOfEmployees = Collections.emptyList();
            }

            return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees);
        }).toList();
    }

    public StockResponseDTO getStock(Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException());

        if(!stock.getCompany().getId().equals(company.getId())){
            throw new StockNotFoundException();
        }

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(stock.getEmployeesWithAccess()!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees);
    }
}
