package com.vitorrmarcelino.stock_manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="company")
@Table(name="company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer id;

    @Column(name = "company_name", length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "company_cnpj", length = 14, nullable = false, unique = true)
    private String cnpj;

    @OneToOne
    @JoinColumn(name = "company_user_fk", nullable = false)
    private User user;
}
