package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    protected UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printInformation(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                   ModelMap model) {
        model.addAttribute("userObj", userService.getUserByUsername(user.getUsername()));
        return "user";
    }
}
