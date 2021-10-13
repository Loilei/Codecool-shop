package com.codecool.shop.controller;

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

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore,
                cartDataStore, supplierDataStore);


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        context.setVariable("productsInCart", productService.getProductsFromCart());
        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());
        context.setVariable("totalPrice", productService.getTotalPrice());
        context.setVariable("productsAndQuantities", productService.getProductsAndQuantities());

        engine.process("product/cart.html", context, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)	throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore,
                cartDataStore, supplierDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String quantityString = req.getParameter("quantity");
        if (!(quantityString == null)) {
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            //todo
            HashMap<Product, Integer> productsAndQuantities = productService.getProductsAndQuantities();

        }

        String productToRemoveFromCartIdString = req.getParameter("productId");
        productService.removeProductFromCart(req, productService, productToRemoveFromCartIdString);

        context.setVariable("productsInCart", productService.getProductsFromCart());
        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());
        context.setVariable("totalPrice", productService.getTotalPrice());
        context.setVariable("productsAndQuantities", productService.getProductsAndQuantities());

        engine.process("product/cart.html", context, resp.getWriter());
    }

}
