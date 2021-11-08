package com.example.springdata.controller;

import com.example.springdata.cart.Cart;
import com.example.springdata.entity.Product;
import com.example.springdata.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;


@Controller
@RequestMapping("/cart")
public class CartController {
    private Cart cart;
    private ProductService productService;

    @Autowired
    public CartController(Cart cart, ProductService productsService) {
        this.cart = cart;
        this.productService = productsService;
    }

    @GetMapping
    public String showAllProductInCart(Model model) {
        model.addAttribute("products", cart.getProducts());
        return "cart";
    }


    @Transactional
    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable Long id) {
        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setPrice(productService.getById(id).getPrice());
        newProduct.setTitle(productService.getById(id).getTitle());
        cart.addProduct(newProduct);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductCart(@PathVariable Long id) {
        cart.deleteProductById(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearProductFromCart() {
        cart.clearProducts();
        return "redirect:/cart";
    }
}
