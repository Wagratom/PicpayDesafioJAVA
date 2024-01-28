package com.expickpay.pickpay.service;

import com.expickpay.pickpay.dtos.UserDto;
import com.expickpay.pickpay.entity.UserEntity;
import com.expickpay.pickpay.excecoes.InvalidIdException;
import com.expickpay.pickpay.excecoes.UserNotFoundException;
import com.expickpay.pickpay.reposity.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        if (id == null) {
            throw new InvalidIdException("Error: getUserById: Id is null or invalid");
        }
        return this.usersRepository.findById(id).orElseThrow (
                ()-> new UserNotFoundException("Invalid id")
        );
    }

    public UserEntity getReferrerById(Long id) {
        if (id == null) {
            throw new InvalidIdException("Error: getReferrerById: Id is null or invalid");
        }
        if (!this.usersRepository.existsById(id)) {
            throw new UserNotFoundException("Error: getReferrerById: User not found");
        }
        return this.usersRepository.getReferenceById(id);
    }
    public void updateAmount(UserEntity user, BigDecimal amount){
        if (user == null) {
            throw new IllegalArgumentException("Error: updateAmount: updateAmount: User is null or invalid");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Error: updateAmount: updateAmount: Amount is null or invalid");
        }
        user.setAmount(user.getAmount().subtract(amount));
        this.usersRepository.save(user);
    }
}
