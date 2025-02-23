package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
