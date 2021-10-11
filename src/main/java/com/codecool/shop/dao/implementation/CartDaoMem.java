package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<Product> productsList = new ArrayList<>();
    private static CartDaoMem instance = null;

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        productsList.add(product);
    }

    @Override
    public void remove(Product product) {
        productsList.remove(product);
    }

    @Override
    public List<Product> getAll() {
        return productsList;
    }

    @Override
    public BigDecimal getTotalSum() {
        BigDecimal totalSum = new BigDecimal("0");
        for(Product product : productsList) {
            totalSum = totalSum.add(product.getDefaultPrice());
        }
        return totalSum;
    }

    @Override
    public HashMap<Product, Integer> getProductQuantities() {
        HashMap<Product, Integer> productsQuantities = new HashMap<>();
        for (Product product : productsList) {
            if (productsQuantities.containsKey(product)) {
                productsQuantities.put(product, productsQuantities.get(product)+1);
            } else {
                productsQuantities.put(product, 1);
            }
        }
        return productsQuantities;
    }
}
