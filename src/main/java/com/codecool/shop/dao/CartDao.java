package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface CartDao {

    void add(Product product);
    void remove(Product product);
    List<Product> getAll();
    BigDecimal getTotalSum();
    HashMap<Product, Integer> getProductQuantities();
//    void setProductsQuantites(HashMap<Product, Integer> currentProductsAndQuantities);
}
