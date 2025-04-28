package com.vitorrmarcelino.stock_manager.controller;

import com.vitorrmarcelino.stock_manager.dto.stock.StockRequestDTO;
import com.vitorrmarcelino.stock_manager.dto.stock.StockResponseDTO;
import com.vitorrmarcelino.stock_manager.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity createStock(@Valid @RequestBody StockRequestDTO stockRequestDTO) {
        StockResponseDTO res = stockService.createStock(stockRequestDTO);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStock(@Valid @RequestBody StockRequestDTO stockRequestDTO, @PathVariable Integer id) {
        StockResponseDTO res = stockService.updateStock(stockRequestDTO, id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("all")
    public ResponseEntity getAllStocks() {
        List<StockResponseDTO> res = stockService.getAllStocks();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity getStock(@PathVariable Integer id) {
        StockResponseDTO res = stockService.getStock(id);
        return ResponseEntity.ok(res);
    }
}
