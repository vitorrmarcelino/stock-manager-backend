package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {
    public UserDetails findByEmail(String email);
}
