package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.dto.product.ProductRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.product.ProductSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.exception.CompanyNotFoundException;
import com.vitorrmarcelino.stock_manager.exception.ProductNotFoundException;
import com.vitorrmarcelino.stock_manager.model.Company;
import com.vitorrmarcelino.stock_manager.model.Product;
import com.vitorrmarcelino.stock_manager.model.User;
import com.vitorrmarcelino.stock_manager.repository.CompanyRepository;
import com.vitorrmarcelino.stock_manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public ProductSimpleResponseDTO createProduct(ProductRequestDTO data){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Product product = new Product();
        product.setName(data.name());
        product.setCompany(company);

        productRepository.save(product);

        return new ProductSimpleResponseDTO(product.getId(), product.getName());
    }

    public List<ProductSimpleResponseDTO> getAllProducts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        List<Product> products = productRepository.findAllByCompany(company);

        List<ProductSimpleResponseDTO> listOfProducts;

        if(!products.isEmpty()){
            listOfProducts = products.stream().map(product -> new ProductSimpleResponseDTO(product.getId(), product.getName())).toList();
        } else {
            listOfProducts = Collections.emptyList();
        }

        return listOfProducts;
    }

    public ProductSimpleResponseDTO getProduct(Integer id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        Company company = companyRepository.findByUser((User) userCompany);

        if(company == null){
            throw new CompanyNotFoundException("You must be a company");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());

        if(!product.getCompany().getId().equals(company.getId())){
            throw new ProductNotFoundException();
        }

        return new ProductSimpleResponseDTO(product.getId(), product.getName());
    }
}
