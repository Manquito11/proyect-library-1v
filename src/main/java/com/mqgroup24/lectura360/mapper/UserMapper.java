package com.mqgroup24.lectura360.mapper;

import com.mqgroup24.lectura360.dao.NewUserDTO;
import com.mqgroup24.lectura360.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toEntity(NewUserDTO user) {
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }

}
