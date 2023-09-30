package com.example.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String role;

    private String phone;

    private String address;

    private LocalDate birthday;

    private String salary;

    private boolean deleted;

}
