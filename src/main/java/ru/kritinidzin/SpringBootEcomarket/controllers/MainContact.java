package ru.kritinidzin.SpringBootEcomarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContact {
    @GetMapping("/contact")
    public String contactMain(Model model) {
        return "contact";
    }
}