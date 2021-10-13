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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductService {
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

    public ProductCategory getProductCategory(int categoryId) {
        return productCategoryDao.find(categoryId);
    }

    public List<Product> getProductsForCategory(int categoryId) {
        var category = productCategoryDao.find(categoryId);
        return productDao.getBy(category);
    }

    public ProductCategory getDefaultCategory() {
        return productCategoryDao.getDefaultCategory();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Product> getProductsFromCart() {
        return cartDao.getAll();
    }

    public List<Product> getSingularProductsFromCart() {
        List<Product> productsList = new ArrayList<>();
        for (Product product : getProductsFromCart()) {
            if (!productsList.contains(product)) {
                productsList.add(product);
            }
        }
        return productsList;
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

    public void removeProductFromCart(HttpServletRequest req, String productToRemoveFromCartIdString) {
        if (!(productToRemoveFromCartIdString == null)) {
            int productToRemoveFromCartId = Integer.parseInt(productToRemoveFromCartIdString);
            Product productToRemove = getProductbyId(productToRemoveFromCartId);
            removeFromCart(productToRemove);
        }
    }

    public void updateCart(HttpServletRequest req, String quantityString) {
        if (!(quantityString == null)) {
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            int productId = Integer.parseInt(req.getParameter("updatedProductId"));
            Product product = getProductbyId(productId);
            if (quantity <= 0) {
                removeItemsFromCartbyId(productId, product);
            } else {
                removeItemsFromCartbyId(productId, product);
                for (int i = 0; i < quantity; i++) {
                    addToCart(product);
                }
            }
        }
    }

    private void removeItemsFromCartbyId(int productId, Product product) {
        int numberOfRemovedItems = getProductsAndQuantities().get(product);
        for (int i = 0; i < numberOfRemovedItems; i++) {
            cartDao.remove(product);
        }
    }
}
