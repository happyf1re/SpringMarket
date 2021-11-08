package com.example.springdata.service;


import com.example.springdata.entity.Product;
import com.example.springdata.repository.ProductRepository;
import com.example.springdata.repository.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;



@Service
public class ProductService {

    public ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    @Transactional
    public List<Product> getAllMoreThanMax(int max) {
        return productRepository.findAllByPriceGreaterThanEqual(max);
    }

    @Transactional
    public Object getAllLessThanMin(int min) {
        return productRepository.findAllByPriceIsLessThanEqual(min);
    }

    @Transactional
    public Object getAllBetweenMaxAndMin(int max, int min) {
        return productRepository.findAllByPriceBetween(max, min);
    }

    @Transactional
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Transactional
    public void removeById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }


    @Transactional
    public Page<Product> getByParams(Optional<String> nameFilter,
                                     Optional<BigDecimal> min,
                                     Optional<BigDecimal> max,
                                     Optional<Integer> page,
                                     Optional<Integer> size,
                                     Optional<String> sortField,
                                     Optional<String> sortOrder) {
        Specification<Product> specification = Specification.where(null);

        if (nameFilter.isPresent()) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter.get()));
        }

        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.ge(min.get()));
        }

        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.le(max.get()));
        }

        if (sortField.isPresent() && sortOrder.isPresent()) {
            return productRepository.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(5),
                    Sort.by(Sort.Direction.fromString(sortOrder.get()), sortField.get())));
        }

        return productRepository.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
    }
}
