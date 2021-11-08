package com.example.springdata.repository;

import com.example.springdata.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //List<Product> findAllByPriceBetweenOrderById ();
    List<Product> findAllByPriceGreaterThanEqual (int max);
    List<Product> findAllByPriceIsLessThanEqual (int min);
    List<Product> findAllByPriceBetween (int min, int max);
}
