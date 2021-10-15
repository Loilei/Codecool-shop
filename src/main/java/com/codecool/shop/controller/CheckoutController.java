package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore,
                cartDataStore, supplierDataStore, orderDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Order order = new Order(productService.getProductsFromCart());

        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)	throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore,
                cartDataStore, supplierDataStore, orderDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        Order order = new Order(productService.getProductsFromCart());
        productService.addToOrder(order);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("phoneNumber");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String zipcode = req.getParameter("zipcode");
        String address = req.getParameter("address");

        productService.setOrderDetails(order, name, email, mobile, country, city, zipcode, address);
        productService.saveOrderToJson(order);

        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());

        engine.process("product/category.html", context, resp.getWriter());
    }

}
