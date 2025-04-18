package com.vitorrmarcelino.stock_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL)
    private List<StockProduct> ProductsInThisStock;

    @ManyToMany(mappedBy = "stocksWithAccess",cascade = CascadeType.ALL)
    private List<Employee> employeesWithAccess;

}
