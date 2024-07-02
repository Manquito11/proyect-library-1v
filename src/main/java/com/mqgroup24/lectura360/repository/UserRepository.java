package com.mqgroup24.lectura360.repository;

import com.mqgroup24.lectura360.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(@Param(("username")) String username);
    User findByUsernameAndPassword(@Param(("username")) String username, @Param(("password")) String password);
    User findByEmail(@Param(("email")) String email);
    User findByEmailAndPassword(@Param(("email")) String username, @Param(("password")) String password);
}
