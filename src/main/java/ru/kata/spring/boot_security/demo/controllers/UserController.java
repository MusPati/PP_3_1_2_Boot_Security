package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    public String authpage() {
        return "privet";
    }


}
