package com.example.hibernateJpa.repository;

import com.example.hibernateJpa.domain.User;
import java.util.List;

public interface UserRepository {
    List<User> findAllUsers();
    User findUserById(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
}
