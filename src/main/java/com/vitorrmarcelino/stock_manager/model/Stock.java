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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "stock_employee",
            joinColumns = @JoinColumn(name="stock_employee_stock_fk"),
            inverseJoinColumns = @JoinColumn(name = "stock_employee_employee_fk")
    )
    private List<Employee> employeesWithAccess;

}
