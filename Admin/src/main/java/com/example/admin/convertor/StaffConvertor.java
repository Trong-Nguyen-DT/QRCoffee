package com.example.admin.convertor;

import com.example.admin.domain.User;
import com.example.admin.entity.UserEntity;

public class StaffConvertor {

    public static User toModel(UserEntity userEntity)
    {
        User user = new User();
      user.setId(userEntity.getId());
      user.setName(userEntity.getName());
      user.setAddress(userEntity.getAddress());
      user.setBirthday(userEntity.getBirthday());
      user.setPassword(userEntity.getPassword());
      user.setUsername(userEntity.getUsername());
      user.setPhone(userEntity.getPhone());
      user.setSalary(userEntity.getSalary());
      user.setDeleted(userEntity.getDeleted());
      return user;

    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(user.getName());
        userEntity.setAddress(user.getAddress());
        userEntity.setBirthday(user.getBirthday());
        userEntity.setPassword(user.getPassword());
        userEntity.setUsername(user.getUsername());
        userEntity.setPhone(user.getPhone());
        userEntity.setSalary(user.getSalary());
        userEntity.setRole("ROLE_STAFF");
        userEntity.setDeleted(true);

        return userEntity;
    }
}
