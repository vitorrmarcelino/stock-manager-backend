package com.vitorrmarcelino.stock_manager.repository;

import com.vitorrmarcelino.stock_manager.model.Employee;
import com.vitorrmarcelino.stock_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByUser(User user);
}
