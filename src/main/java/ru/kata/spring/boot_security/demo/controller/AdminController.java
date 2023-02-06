package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    UserService userService;

    RoleService roleService;

    @Autowired
    protected AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String printUsers(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                             ModelMap model) {
        /* Вывод всех пользователей в виде таблицы */
        List<User> usersList = userService.getAllUsers();
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("currentUser", user);
        model.addAttribute("users", usersList);
        return "admin";
    }

    @GetMapping("search")
    public String printUser(@RequestParam(value = "id", required = false, defaultValue = "0") long id,
                            ModelMap model) {
        /* Вывод пользователя при поиске по id */
        model.addAttribute("users", List.of(userService.findUserById(id)));
        return "admin";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "nameRoles") String[] roles) {
        /* Сохранение пользователя */
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @PostMapping("edit")
    public String updateUserInfo(@ModelAttribute("user") User user,
                                 @RequestParam(value = "editRoles") String[] roles,
                                 @RequestParam("password") String password) {
        /* Обновление данных пользователя */
        user.setPassword(password);
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
         /* Удаление пользователя по id */
         userService.deleteUser(id);
         return "redirect:/admin";
    }
}