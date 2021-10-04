package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier razer = new Supplier("Razer", "Peripherials");
        supplierDataStore.add(razer);
        Supplier asus = new Supplier("ASUS", "Peripherials");
        supplierDataStore.add(asus);
        Supplier roccat = new Supplier("Roccat", "Peripherials");
        supplierDataStore.add(roccat);

        //setting up a new product category
        ProductCategory tablets = new ProductCategory("Tablets", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablets);
        ProductCategory mice = new ProductCategory("Mice", "Hardware", "A mouse is a mouse.");
        productCategoryDataStore.add(mice);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablets, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablets, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablets, amazon));
        productDataStore.add(new Product("Razer DeathAdder V2 Pro Wireless", new BigDecimal("189"), "USD", "Wireless gaming mouse with best-in-class ergonomics.", mice, razer));
        productDataStore.add(new Product("ASUS TUF Gaming M3 Optical Gaming Mouse", new BigDecimal("79"), "USD", "Ergonomic and lightweight for comfortable high-speed maneuvering using claw or palm grip.", mice, asus));
        productDataStore.add(new Product("Roccat Kone Pro Air Gaming Mouse", new BigDecimal("219"), "USD", "Iconic ergonomic shape refined to perfection.", mice, roccat));
    }
}
