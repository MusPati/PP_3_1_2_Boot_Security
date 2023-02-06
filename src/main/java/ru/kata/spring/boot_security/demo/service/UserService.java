package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService extends UserDetailsService {

    void saveUser(User user, String[] roles);

    void updateUser(User user, String[] roles);

    void deleteUser(long id);

    List<User> getAllUsers();

    User findUserById(long id);

    User getUserByUsername(String username);
}
