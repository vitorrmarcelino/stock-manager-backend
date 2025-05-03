package com.vitorrmarcelino.stock_manager.dto.stock;

import com.vitorrmarcelino.stock_manager.dto.employee.EmployeeSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.dto.product.ProductSimpleResponseDTO;

import java.util.List;

public record StockResponseDTO(Integer id, String name, List<EmployeeSimpleResponseDTO> employees, List<StockProductResponseDTO> products) {
}
