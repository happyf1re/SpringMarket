//package com.example.springdata.controller;
//
//import com.example.springdata.entity.Product;
//import com.example.springdata.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@org.springframework.web.bind.annotation.RestController
//@RequestMapping("/api/v1/product")
//public class RestController {
//
//    private ProductService service;
//
//    @Autowired
//    public void setService(ProductService service) {
//        this.service = service;
//    }
//
//    @GetMapping(path = "/{id}/id", produces = "application/json")
//    public Product findById(@PathVariable("id") Long id) {
//        return service.getById(id);
//    }
//
//    @GetMapping(path = "/list", produces = "application/json")
//    public List<Product> findAll() {
//        return service.getAllProduct();
//    }
//
//    @PostMapping(consumes = "application/json", produces = "application/json")
//    public Product createProduct(@RequestBody Product product) {
//        service.addOrUpdate(product);
//        return product;
//    }
//
//    @PutMapping(consumes = "application/json", produces = "application/json")
//    public Product updateProduct(@RequestBody Product product) {
//        service.addOrUpdate(product);
//        return product;
//    }
//
//    @DeleteMapping("/{id}/id")
//    public void deleteById(@PathVariable("id") Long id) {
//        service.removeById(id);
//    }
//}
