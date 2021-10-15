package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

public interface OrderDao {
    void add(Order order);
    void remove(Order order);
    List<Order> getAll();
    void setOrderDetails(Order order, String name, String email, String mobile, String country, String city,
                         String zipcode, String address);
}
