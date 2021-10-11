package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Order extends BaseModel {

  private Map<Product,Integer> items;

  public Order(String name) {
    super(name);
    this.items = new HashMap<>();
  }

  public Map<Product,Integer> getItems() {
    return this.items;
  }

  public void addItem(Product product) {
//    this.items.put(product, this.items.getOrDefault(product, 0) + 1);
//
    if (this.items.containsKey(product)) {
      this.items.put(product, this.items.get(product)+1);
    } else {
      int defaultQuantity = 1;
      this.items.put(product, defaultQuantity);
    }
  }

  public void removeItem(Product product) {
    this.items.remove(product);
  }

  public void removeAllItems() {
    this.items.clear();
  }

  public void editQuantity(Product product, int quantity) {
    this.items.put(product, quantity);
  }

  @Override
  public String toString() {
    return "Order{" +
      "items=" + items +
      '}';
  }
}
