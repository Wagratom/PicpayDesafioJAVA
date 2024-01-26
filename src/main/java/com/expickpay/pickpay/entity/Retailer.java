package com.expickpay.pickpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_retailer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;
}

