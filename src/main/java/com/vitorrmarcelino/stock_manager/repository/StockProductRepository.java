package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockProductRepository extends JpaRepository<StockProduct, Integer> {
}
