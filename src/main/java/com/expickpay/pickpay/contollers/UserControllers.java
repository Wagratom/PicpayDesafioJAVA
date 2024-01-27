package com.expickpay.pickpay.contollers;

import com.expickpay.pickpay.dtos.UserDto;
import com.expickpay.pickpay.entity.UserEntity;
import com.expickpay.pickpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllers {
    private final UserService userService;
    @Autowired
    public UserControllers(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        List<UserEntity> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @PostMapping("/create")
    public ResponseEntity<UserEntity> hello(@RequestBody UserDto dto) {
        System.out.println(dto);
        UserEntity newUser = this.userService.createUser(dto);
       return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

}
