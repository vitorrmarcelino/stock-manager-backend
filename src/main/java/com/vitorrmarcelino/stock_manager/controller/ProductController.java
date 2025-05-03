package com.vitorrmarcelino.stock_manager.controller;

import com.vitorrmarcelino.stock_manager.dto.product.ProductRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.product.ProductSimpleResponseDTO;
import com.vitorrmarcelino.stock_manager.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
        ProductSimpleResponseDTO res = productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductSimpleResponseDTO> res = productService.getAllProducts();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Integer id){
        ProductSimpleResponseDTO res = productService.getProduct(id);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequestDTO productRequestDTO){
        ProductSimpleResponseDTO res = productService.updateProduct(productRequestDTO, id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
