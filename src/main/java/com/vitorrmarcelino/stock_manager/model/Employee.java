package com.vitorrmarcelino.stock_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name="employee")
@Entity(name="employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "employee_name", length = 50, nullable = false)
    private String name;

    @Column(name = "employee_cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "employee_user_fk", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "employee_company_fk", nullable = false)
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "stock_employee",
            joinColumns = @JoinColumn(name="stock_employee_employee_fk"),
            inverseJoinColumns = @JoinColumn(name = "stock_employee_stock_fk")
    )
    private List<Stock> stocksWithAccess;
}
