package com.expickpay.pickpay.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserControllers {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/hello2")
    public ResponseEntity<String> hello2(){
        return ResponseEntity.ok("Hello World");
    }
}
