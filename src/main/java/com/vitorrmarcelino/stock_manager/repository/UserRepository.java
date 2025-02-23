package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
