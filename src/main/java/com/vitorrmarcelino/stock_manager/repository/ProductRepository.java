package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.Company;
import com.vitorrmarcelino.stock_manager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAllByCompany(Company company);
}
