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
public class SignUpController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public String signUp(Model model) {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUpPost(@RequestParam String login,
                              @RequestParam String password,
                             @RequestParam String role,
                              Model model) throws Exception {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        boolean result = userService.insert(user);
        if (result == true) {
            System.out.println("insert success");
        } else {
            System.out.println("insert failed");
        }
        return "redirect:/";
    }
}
