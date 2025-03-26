package com.vitorrmarcelino.stock_manager.service;

import com.vitorrmarcelino.stock_manager.exception.CnpjAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.exception.EmailAlreadyUsedException;
import com.vitorrmarcelino.stock_manager.model.User;
import com.vitorrmarcelino.stock_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void deleteUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User userCompany = (User)principal;

        userRepository.delete(userCompany);

        return;
    }
}
