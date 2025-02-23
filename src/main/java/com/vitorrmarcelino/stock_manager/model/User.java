package com.vitorrmarcelino.stock_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Table(name="\"user\"")
@Entity(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "user_password", length = 255, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Company company;

    @OneToOne(mappedBy = "user")
    private  Employee employee;
}
