package com.example.springdata.controller;


import com.example.springdata.entity.Product;
import com.example.springdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;


@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public String indexPage(Model model,
                            @RequestParam(name = "titleFilter", required = false) Optional<String> titleFilter,
                            @RequestParam(name = "min", required = false) Optional<BigDecimal> min,
                            @RequestParam(name = "max", required = false) Optional<BigDecimal> max,
                            @RequestParam(name = "page", required = false) Optional<Integer> page,
                            @RequestParam(name = "size", required = false) Optional<Integer> size,
                            @RequestParam(name = "sortField", required = false) Optional<String> sortField,
                            @RequestParam(name = "sortOrder", required = false) Optional<String> sortOrder

    ) {
        model.addAttribute("products",
                productService.getByParams(
                        titleFilter,
                        min,
                        max,
                        page,
                        size,
                        sortField,
                        sortOrder));
        return "index";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product_form";
    }

    @PostMapping("/product_update")
    public String updateProduct(Product product) {
        productService.addOrUpdate(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute(new Product());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productService.removeById(id);
        return "redirect:/product";
    }

//    @GetMapping("/cart")
//    public String showAllProductsInCart() {
//        cartService.getAllProductInTheCart();
//        return "cart";
//    }
//
////    @PostMapping("cart/cart_update")
////    public String addAndUpdateCart(Model model, Long id) {
////        model.addAttribute("product", productService.getById(id));
////        return "index";
////    }
//
//    @PutMapping("/cart_update")
//    public String updateCart(Product product) {
//        cartService.addToCart(product.getId());
//        return "redirect:/product";
//    }
}
