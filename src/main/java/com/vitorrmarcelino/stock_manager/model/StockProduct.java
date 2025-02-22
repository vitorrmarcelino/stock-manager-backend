package com.vitorrmarcelino.stock_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "stock_product")
@Table(name = "stock_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_product_id")
    private Integer id;

    @Column(name = "stock_product_qty", nullable = false)
    private Integer qty;

    @ManyToOne
    @JoinColumn(name = "stock_product_stock_fk", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "stock_product_product_fk", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "stockProduct")
    private List<Transaction> transactions;
}
