package com.vitorrmarcelino.stock_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "stock")
@Entity(name = "stock")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer id;

    @Column(name = "stock_name", length = 20, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "stock_company_fk", nullable = false)
    private Company company;

}
