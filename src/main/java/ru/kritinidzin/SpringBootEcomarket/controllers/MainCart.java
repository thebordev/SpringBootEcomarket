package ru.kritinidzin.SpringBootEcomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kritinidzin.SpringBootEcomarket.models.Product;
import ru.kritinidzin.SpringBootEcomarket.service.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainCart {
    @Autowired
    private ProductService productService;
//    @GetMapping("/cart")
//    public String cartMain(Model model) {
//        Iterable<Product> products = productService.selectMany();
//        model.addAttribute("products", products);
//        return "cart";
//    }

    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String getCookies(HttpServletRequest request, Model model) throws Exception {
        List<Product> productList = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("cart")) {
                    Product product = productService.selectOne(Long.valueOf(cookie.getValue()));
                    productList.add(product);
                }
            }
        Iterable<Product> products = productList;
        model.addAttribute("products", products);
        return "cart";
    }
}
