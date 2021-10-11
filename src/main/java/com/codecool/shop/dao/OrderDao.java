package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public interface OrderDao {

  void set(Order order);
  Order get();
  void remove();
  void edit(Order order);
}
