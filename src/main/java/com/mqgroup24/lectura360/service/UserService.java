package com.mqgroup24.lectura360.service;

import com.mqgroup24.lectura360.entity.User;
import com.mqgroup24.lectura360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByCrendentials(String usernameOrEmail, String password) {
        return !isValidEmailAddress(usernameOrEmail) ? userRepository.findByUsernameAndPassword(usernameOrEmail, password) : userRepository.findByEmailAndPassword(usernameOrEmail, password);
    }

    private boolean isValidEmailAddress(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

}
