package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {
    private List<Order> orders = new ArrayList<>();
    private static OrderDaoMem instance = null;

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        order.setId(orders.size()+1);
        orders.add(order);
    }

    @Override
    public void remove(Order order) {
        orders.remove(order);

    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public void setOrderDetails (Order order, String name, String email, String mobile, String country, String city,
                            String zipcode, String address) {
    order.setName(name);
    order.setEmail(email);
    order.setPhoneNumber(mobile);
    order.setCountry(country);
    order.setCity(city);
    order.setZipcode(zipcode);
    order.setAddress(address);
    }
}
