package com.vitorrmarcelino.stock_manager.model;

import com.vitorrmarcelino.stock_manager.type.TransactionTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="transaction")
@Table(name = "transaction")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "transaction_type", nullable = false)
    private TransactionTypeEnum type;

    @ManyToOne
    @JoinColumn(name="transaction_stock_product_fk")
    private StockProduct stockProduct;
}
