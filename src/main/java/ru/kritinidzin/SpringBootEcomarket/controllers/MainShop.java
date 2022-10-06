package ru.kritinidzin.SpringBootEcomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kritinidzin.SpringBootEcomarket.models.Product;
import ru.kritinidzin.SpringBootEcomarket.service.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MainShop {

    @Autowired
    private ProductService productService;

    @GetMapping("/shop")
    public String shopMain(Model model) {
        Iterable<Product> products = productService.selectMany();
        model.addAttribute("products", products);
        return "shop";
    }
    @GetMapping("/shopFruit")
    public String shopMainFruit(Model model) {
        Iterable<Product> products = productService.selectCategory("Fruit");
        model.addAttribute("products", products);
        return "shop";
    }
    @GetMapping("/shopMeat")
    public String shopMainMeat(Model model) {
        Iterable<Product> products = productService.selectCategory("Meat");
        model.addAttribute("products", products);
        return "shop";
    }
    @GetMapping("/shopVegetables")
    public String shopMainVegetables(Model model) {
        Iterable<Product> products = productService.selectCategory("Vegetables");
        model.addAttribute("products", products);
        return "shop";
    }
    @GetMapping("/shopBakal")
    public String shopMainBakal(Model model) {
        Iterable<Product> products = productService.selectCategory("Bakal");
        model.addAttribute("products", products);
        return "shop";
    }
    @GetMapping("/shopChemistry")
    public String shopMainChemistry(Model model) {
        Iterable<Product> products = productService.selectCategory("Chemistry");
        model.addAttribute("products", products);
        return "shop";
    }
    @RequestMapping(value = "/shop/{productId}")
    public String findProductDetail(@PathVariable(value = "productId")Long productId, Model model) throws Exception {
        model.addAttribute("product", productService.selectOne(productId));
        return "shop";
    }
    @RequestMapping(value = "/shop/select/{productId}", params = "addCart")
    public String selectProductDetail(@PathVariable(value = "productId")String productId,
                                      Model model,
                                      HttpServletResponse response) throws Exception {
        Product product = productService.selectOne(Long.valueOf(productId));
        model.addAttribute("product", productService.selectOne(Long.valueOf(productId)));
        Cookie cookie = new Cookie("cart_" + productId, productId);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        return "cart";
    }
    public String setCookie() {


        return "Username is changed!";
    }
}
