package com.codecool.shop.config;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
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
        CartDao cartDataStore = CartDaoMem.getInstance();

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
        Supplier steelSeries = new Supplier("SteelSeries", "Keyboards");
        supplierDataStore.add(steelSeries);
        Supplier dynamix = new Supplier("Dynamix", "MonitorCables");
        supplierDataStore.add(dynamix);
        Supplier alogic = new Supplier("Alogic", "MonitorCables");
        supplierDataStore.add(alogic);
        Supplier acer = new Supplier("Acer", "Monitor");
        supplierDataStore.add(acer);
        Supplier aoc = new Supplier("AOC", "Monitor");
        supplierDataStore.add(aoc);


        //setting up a new product category
        ProductCategory tablets = new ProductCategory("Tablets", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablets);
        ProductCategory mice = new ProductCategory("Mice", "Hardware", "A mouse is a mouse.");
        productCategoryDataStore.add(mice);
        ProductCategory keyboard = new ProductCategory("Keyboard", "Hardware", "A keyboard. Click, click.");
        productCategoryDataStore.add(keyboard);
        ProductCategory monitorCable = new ProductCategory("Monitor Cable", "Hardware", "Plug your monitor and start using it.");
        productCategoryDataStore.add(monitorCable);
        ProductCategory monitor = new ProductCategory("Monitor", "Hardware", "Will show you the world once plugged.");
        productCategoryDataStore.add(monitor);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", new BigDecimal("49.9"), "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablets, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", new BigDecimal("479"), "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablets, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", new BigDecimal("89"), "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablets, amazon));
        productDataStore.add(new Product("Razer DeathAdder V2 Pro Wireless", new BigDecimal("189"), "USD", "Wireless gaming mouse with best-in-class ergonomics.", mice, razer));
        productDataStore.add(new Product("ASUS TUF Gaming M3 Optical Gaming Mouse", new BigDecimal("79"), "USD", "Ergonomic and lightweight for comfortable high-speed maneuvering using claw or palm grip.", mice, asus));
        productDataStore.add(new Product("Roccat Kone Pro Air Gaming Mouse", new BigDecimal("219"), "USD", "Iconic ergonomic shape refined to perfection.", mice, roccat));
        productDataStore.add(new Product("SteelSeries Apex Pro TKL Mechanical Gaming Keyboard", new BigDecimal("399"), "USD", "The Next Leap in Mechanical Keyboards.", keyboard, steelSeries));
        productDataStore.add(new Product("Roccat Pyro Mechanical Gaming Keyboard", new BigDecimal("189"), "USD", "Mechanical switches with 2,0mm actuation.", keyboard, roccat));
        productDataStore.add(new Product("ASUS ROG Strix Scope TKL Mechanical RGB", new BigDecimal("249"), "USD", "Created for FPS Gamers.", keyboard, asus));
        productDataStore.add(new Product("DYNAMIX 3m DisplayPort V1.4 Cable. (FUHD)", new BigDecimal("29"), "USD", "Supports up to 8K.", monitorCable, dynamix));
        productDataStore.add(new Product("DYNAMIX 3M HDMI 2.0 Nano High Speed", new BigDecimal("19"), "USD", "Slimline Robust Cable.", monitorCable, dynamix));
        productDataStore.add(new Product("ALOGIC Premium 15cm Mini DisplayPort to VGA", new BigDecimal("25"), "USD", "Connect Mini DisplayPort Source to a VGA Display.", monitorCable, alogic));
        productDataStore.add(new Product("Acer CB342CK 34 UWQHD 1ms 75Hz IPS", new BigDecimal("599"), "USD", "ZeroFrame display, allowing you to see more and do more.", monitor, acer));
        productDataStore.add(new Product("ASUS VA24DQLB 23.8 75Hz Full HD", new BigDecimal("329"), "USD", "Ergonomic IPS Monitor.", monitor, asus));
        productDataStore.add(new Product("AOC Q27G2S 27\" 155Hz WQHD 1ms G-Sync", new BigDecimal("549"), "USD", "G-Sync Compatible Gaming Monitor.", monitor, aoc));
    }
}
