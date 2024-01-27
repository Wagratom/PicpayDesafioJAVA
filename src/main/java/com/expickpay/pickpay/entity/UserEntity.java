package com.expickpay.pickpay.entity;

import com.expickpay.pickpay.dtos.UserDto;
import com.expickpay.pickpay.enums.UserTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserTypes userType;

    private BigDecimal amount;

    public UserEntity(UserDto dataUser) {
        this.username = dataUser.username();
        this.cpf = dataUser.cpf();
        this.email = dataUser.email();
        this.password = dataUser.password();
        this.userType = UserTypes.valueOf(dataUser.UserType());
        this.amount = dataUser.amount();
    }
}

