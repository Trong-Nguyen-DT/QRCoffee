package com.example.admin.convertor;

import com.example.admin.domain.User;
import com.example.admin.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class StaffConvertor {

    public static User toModel(UserEntity userEntity)
    {
        User user = new User();
      user.setId(userEntity.getId());
      user.setName(userEntity.getName());
      user.setAddress(userEntity.getAddress());
      user.setBirthday(userEntity.getBirthday());
//      user.setPassword(userEntity.getPassword());
      user.setUsername(userEntity.getUsername());
      user.setPhone(userEntity.getPhone());
      user.setSalary(userEntity.getSalary());
      user.setDeleted(userEntity.getDeleted());
      return user;

    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        userEntity.setName(user.getName());
        userEntity.setAddress(user.getAddress());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setUsername(user.getUsername());
        userEntity.setPhone(user.getPhone());
        userEntity.setSalary(user.getSalary());
        userEntity.setRole("ROLE_STAFF");
        userEntity.setDeleted(true);

        return userEntity;
    }

    public static UserEntity toEntityOrder(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        userEntity.setAddress(user.getAddress());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setPhone(user.getPhone());
        userEntity.setSalary(user.getSalary());

        return userEntity;
    }
}
