package com.example.hibernateJpa.service;

import com.example.hibernateJpa.domain.User;
import com.example.hibernateJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            return userRepository.saveUser(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
