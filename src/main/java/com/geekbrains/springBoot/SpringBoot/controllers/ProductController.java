package com.geekbrains.springBoot.SpringBoot.controllers;

import com.geekbrains.springBoot.SpringBoot.exceptions.ResourceNotFoundException;
import com.geekbrains.springBoot.SpringBoot.model.Product;
import com.geekbrains.springBoot.SpringBoot.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService ;
    // GET http://localhost:8189/app/product/all
    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("frontProduct", productService.getAllProduct());
        return "all_products";
    }


    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        productService.creat(product);
        return "redirect:/product/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        System.out.println("ProductController удаление товара "+id);
        productService.delte(id);
        return "redirect:/product/all";
    }

    @GetMapping("/findID")
    public String findID(@RequestParam(name = "id") Long id,Model model) {
        System.out.println("ProductController поиск товара "+id);
        try {
            model.addAttribute("frontProduct",productService.read(id));
            System.out.println(productService.read(id));
        } catch (ResourceNotFoundException e){
            return "all_products";
        }
        finally {

            System.out.println("пересылка данных на страницу products");
            return "products";
        }
    }





}
