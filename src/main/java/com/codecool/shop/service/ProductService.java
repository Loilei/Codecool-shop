package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import jdk.jfr.Category;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
    private CartDao cartDao;
    private SupplierDao supplierDao;

    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao,
                          CartDao cartDao, SupplierDao supplierDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
        this.cartDao = cartDao;
        this.supplierDao = supplierDao;
    }

    public ProductCategory getProductCategory(int categoryId){
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId){
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public ProductCategory getDefaultCategory() {
        return productCategoryDao.getDefaultCategory();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Product> getProductsFromCart(){
        return cartDao.getAll();
    }

    public int getProductsAmountFromCart() {
        return cartDao.getAll().size();
    }

    public Product getProductbyId(int id) {
        return productDao.find(id);
    }

    public void addToCart(Product product) {
        cartDao.add(product);
    }

    public BigDecimal getTotalPrice() {
        return cartDao.getTotalSum();
    }

    public HashMap<Product, Integer> getProductsAndQuantities() {
        return cartDao.getProductQuantities();
    }

    public void removeFromCart(Product product) {
        cartDao.remove(product);
    }

    public void removeProductFromCart(HttpServletRequest req, ProductService productService, String productToRemoveFromCartIdString) {
        if (!(productToRemoveFromCartIdString == null)) {
            int productToRemoveFromCartId = Integer.parseInt(productToRemoveFromCartIdString);
            Product productToRemove = productService.getProductbyId(productToRemoveFromCartId);
            productService.removeFromCart(productToRemove);
        }
    }
}
