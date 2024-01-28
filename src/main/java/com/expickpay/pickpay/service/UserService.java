package com.expickpay.pickpay.service;

import com.expickpay.pickpay.dtos.UserDto;
import com.expickpay.pickpay.entity.UserEntity;
import com.expickpay.pickpay.excecoes.InvalidIdException;
import com.expickpay.pickpay.reposity.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public UserEntity createUser(UserDto dto) {
        return this.usersRepository.save(new UserEntity(dto));
    }

    public List<UserEntity> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public UserEntity getUserById(Long id) {
        return this.usersRepository.findById(id).orElseThrow(
                ()-> new InvalidIdException("Invalid id")
        );
    }
}
