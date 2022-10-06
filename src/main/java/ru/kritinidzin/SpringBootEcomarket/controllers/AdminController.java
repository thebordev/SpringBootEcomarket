package ru.kritinidzin.SpringBootEcomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kritinidzin.SpringBootEcomarket.forms.ProductForm;
import ru.kritinidzin.SpringBootEcomarket.models.Product;
import ru.kritinidzin.SpringBootEcomarket.service.ProductService;

import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/shopFruitAdm")
    public String shopMainFruit(Model model) {
        Iterable<Product> products = productService.selectCategory("Fruit");
        model.addAttribute("products", products);
        return "adminAll";
    }
    @GetMapping("/shopMeatAdm")
    public String shopMainMeat(Model model) {
        Iterable<Product> products = productService.selectCategory("Meat");
        model.addAttribute("products", products);
        return "adminAll";
    }
    @GetMapping("/shopVegetablesAdm")
    public String shopMainVegetables(Model model) {
        Iterable<Product> products = productService.selectCategory("Vegetables");
        model.addAttribute("products", products);
        return "adminAll";
    }
    @GetMapping("/shopBakalAdm")
    public String shopMainBakal(Model model) {
        Iterable<Product> products = productService.selectCategory("Bakal");
        model.addAttribute("products", products);
        return "adminAll";
    }
    @GetMapping("/shopChemistryAdm")
    public String shopMainChemistry(Model model) {
        Iterable<Product> products = productService.selectCategory("Chemistry");
        model.addAttribute("products", products);
        return "adminAll";
    }

    @GetMapping("/shopAll")
    public String shopAll(Model model) {
        Iterable<Product> products = productService.selectMany();
        model.addAttribute("products", products);
        return "adminAll";
    }

    @GetMapping("/shopAdd")
    public String shopAdd(Model model) {
        return "adminIndex";
    }

    @PostMapping("/shopAdd")
    public String shopPostAdd(@RequestParam String name,
                              @RequestParam String information,
                              @RequestParam String category,
                              @RequestParam int amount,
                              @RequestParam double price,
                              @RequestParam int status,
                              Model model) throws Exception {
        try {
            Product product = new Product(name, information, category, amount, price, status);
            productService.insert(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/shopAll";
    }

    @PostMapping("/shopAll/{productId}")
    public String productDelete(Model model, @PathVariable(value = "productId")Long productId) throws Exception {
        productService.deleteOne(productId);
        return "redirect:/shopAll";
    }

    @GetMapping("/shopUpdate/{id:.+}")
    public String productUpdate(@PathVariable("id") Long id,
                                @ModelAttribute ProductForm form,
                                Model model) throws Exception {
        Product product = productService.selectOne(id);
        form.setId(product.getId());
        form.setStatus(product.getStatus());
        form.setPrice(product.getPrice());
        form.setInformation(product.getInformation());
        form.setAmount(product.getAmount());
        form.setCategory(product.getCategory());
        form.setName(product.getName());
        model.addAttribute("ProductForm", form);
        return "updateProd";
    }

    @PostMapping(value = "/shopUpdate", params = "update")
    public String productUpdatePost(
                              @ModelAttribute ProductForm form,
                              Model model) throws Exception {

        Product product = new Product();

        product.setId(form.getId());
        product.setStatus(form.getStatus());
        product.setPrice(form.getPrice());
        product.setInformation(form.getInformation());
        product.setAmount(form.getAmount());
        product.setCategory(form.getCategory());
        product.setName(form.getName());

        productService.updateOne(product);
        return "redirect:/shopAll";
    }
}
