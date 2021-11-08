package com.example.springdata.cart;

import com.example.springdata.entity.Product;
import com.example.springdata.service.ProductService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> products;
    private ProductService productService;


    @PostConstruct
    @Transactional
    public void init(){
        this.products = new ArrayList<>();
    }

    @Transactional
    public List<Product> getProducts() {
        return products;
    }

    @Transactional
    public void addProduct(Product product) {
        if (!products.contains(product)) {
            this.products.add(product);
        } else {
            product.setAmount(product.getAmount()+1);
        }
        System.out.println(product.getAmount());
    }

    @Transactional
    public void deleteProductById(Long id) {
        for (Product p : products) {
            if (p.getId() == id){
                products.remove(p);
                break;
            }
        }
    }

    @Transactional
    public void clearProducts() {
        products.clear();
    }
}
