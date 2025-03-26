package com.vitorrmarcelino.stock_manager.controller;

import com.vitorrmarcelino.stock_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(){
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }
}
