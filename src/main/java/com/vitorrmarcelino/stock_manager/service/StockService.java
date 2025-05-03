package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.product.ProductSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.product.ProductTransactionRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockProductResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CompanyNotFoundException;
import com.vitorrmarcelino.stock_manager.exception.EmployeeNotFoundException;
import com.vitorrmarcelino.stock_manager.exception.ProductNotFoundException;
import com.vitorrmarcelino.stock_manager.exception.StockNotFoundException;
import com.vitorrmarcelino.stock_manager.model.*;
import com.vitorrmarcelino.stock_manager.repository.*;
import com.vitorrmarcelino.stock_manager.type.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired StockProductRepository stockProductRepository;

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

        List<StockProductResponseDTO> listOfProducts;

        if(stock.getProductsInThisStock()!=null){
            listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
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

        List<StockProductResponseDTO> listOfProducts;

        if(stock.getProductsInThisStock()!=null){
            listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
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

            List<StockProductResponseDTO> listOfProducts;

            if(stock.getProductsInThisStock()!=null){
                listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
            } else {
                listOfProducts = Collections.emptyList();
            }

            return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
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

        List<StockProductResponseDTO> listOfProducts;

        if(stock.getProductsInThisStock()!=null){
            listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
    }

    public StockResponseDTO authorizeEmployees(Integer id, List<Integer> employeesId){
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

        List<Employee> employeesToAdd = employeeRepository.findByIdIn(employeesId);

        List<Employee> currentEmployees = stock.getEmployeesWithAccess();

        for (Employee employee : employeesToAdd) {
            if (!currentEmployees.contains(employee)) {
                currentEmployees.add(employee);
            }
        }

        stock.setEmployeesWithAccess(currentEmployees);

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(currentEmployees!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        List<StockProductResponseDTO> listOfProducts;

        if(stock.getProductsInThisStock()!=null){
            listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        stockRepository.save(stock);

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
    }

    public StockResponseDTO revokeEmployees(Integer id, List<Integer> employeesId){
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

        List<Employee> employeesToRemove = employeeRepository.findByIdIn(employeesId);

        List<Employee> currentEmployees = stock.getEmployeesWithAccess();

        for (Employee employee : employeesToRemove) {
            if (currentEmployees.contains(employee)) {
                currentEmployees.remove(employee);
            }
        }

        stock.setEmployeesWithAccess(currentEmployees);

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(currentEmployees!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(employee -> new EmployeeSimpleResponseDTO(employee.getId(), employee.getName(), employee.getCpf(), employee.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        List<StockProductResponseDTO> listOfProducts;

        if(stock.getProductsInThisStock()!=null){
            listOfProducts = stock.getProductsInThisStock().stream().map(product -> new StockProductResponseDTO(product.getProduct().getId(), product.getProduct().getName(), product.getQty())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        stockRepository.save(stock);

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
    }

    public StockResponseDTO productTransaction(Integer id, ProductTransactionRequestDTO data){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = (User)principal;

        Company company = companyRepository.findByUser((User) user);
        Employee employee = employeeRepository.findByUser((User) user);

        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException());

        if(company==null){
            if(stock.getEmployeesWithAccess().contains(employee)){
                company = employee.getCompany();
            }else{
                throw new StockNotFoundException();
            }
        }

        if(!stock.getCompany().getId().equals(company.getId()) && !stock.getEmployeesWithAccess().contains(employee)){
            throw new StockNotFoundException();
        }


        Transaction transaction = new Transaction();
        transaction.setType(data.type());
        transaction.setQty(data.quantity());

        StockProduct stockProduct = stock.getProductsInThisStock().stream().filter(product -> product.getProduct().getId().equals(data.productId())).findFirst().orElse(null);

        Product product = productRepository.findById(data.productId()).orElseThrow(() -> new ProductNotFoundException());

        if(!product.getCompany().getId().equals(company.getId())){
            throw new ProductNotFoundException();
        }

        if(stockProduct == null){
            if(data.type()== TransactionTypeEnum.out){
                throw new ProductNotFoundException("Product not found in this stock");
            }
            stockProduct = new StockProduct();
            stockProduct.setStock(stock);
            stockProduct.setProduct(product);
            stockProduct.setQty(data.quantity());
        }else{
            switch (data.type()){
                case TransactionTypeEnum.entry:
                    stockProduct.setQty(stockProduct.getQty()+data.quantity());
                    break;
                case TransactionTypeEnum.out:
                    stockProduct.setQty(stockProduct.getQty()-data.quantity());

                    if(stockProduct.getQty() < 0){
                        throw new ProductNotFoundException("Not enough product in stock");
                    }
                    break;
            }

        }

        transaction.setStockProduct(stockProduct);

        stockProductRepository.save(stockProduct);
        transactionRepository.save(transaction);

        List<StockProductResponseDTO> listOfProducts = new ArrayList<>();

        listOfProducts.add(new StockProductResponseDTO(product.getId(), product.getName(), stockProduct.getQty()));

        List<EmployeeSimpleResponseDTO> listOfEmployees;

        if(stock.getEmployeesWithAccess()!=null){
            listOfEmployees = stock.getEmployeesWithAccess().stream().map(e -> new EmployeeSimpleResponseDTO(e.getId(), e.getName(), e.getCpf(), e.getUser().getEmail())).toList();
        } else {
            listOfEmployees = Collections.emptyList();
        }

        return new StockResponseDTO(stock.getId(), stock.getName(), listOfEmployees, listOfProducts);
    }

}
