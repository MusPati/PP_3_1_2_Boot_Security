package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user) throws Exception;

    List<User> listUsers();

    User getUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    List<Role> listRoles();
}
