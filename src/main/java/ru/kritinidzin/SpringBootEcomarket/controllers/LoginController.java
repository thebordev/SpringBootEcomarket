package ru.kritinidzin.SpringBootEcomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kritinidzin.SpringBootEcomarket.models.User;
import ru.kritinidzin.SpringBootEcomarket.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/loginUp")
    public String signUp(Model model) {
        return "loginUp";
    }

    @PostMapping("/loginUp")
    public String signUpPost(@RequestParam String login,
                             Model model) throws Exception {
        User user = userService.selectOne(login);
        System.out.println(user);
        return "loginUp";
    }
}
