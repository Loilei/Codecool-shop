package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
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

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

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

        String categoryName = req.getParameter("categoryName");

        List<Supplier> suppliersList = new ArrayList<>();

        for (Product product : productDataStore.getAll()) {
            if (Objects.equals(categoryName, product.getProductCategory().getName())) {
                if (!suppliersList.contains(product.getSupplier())) {
                    suppliersList.add(product.getSupplier());
                }
            }
        }

        context.setVariable("suppliersList", suppliersList);
        context.setVariable("categoryName", categoryName);
        context.setVariable("categoryProducts", productDataStore.getAll());
        context.setVariable("suppliersNames", supplierDataStore.getAll());
        context.setVariable("filteredSuppliers", supplierDataStore.getAll());
        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());

        engine.process("product/category.html", context, resp.getWriter());
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

        String categoryName = req.getParameter("categoryName");

        List<Supplier> suppliersList = new ArrayList<>();

        for (Product product : productDataStore.getAll()) {
            if (Objects.equals(categoryName, product.getProductCategory().getName())) {
                if (!suppliersList.contains(product.getSupplier())) {
                    suppliersList.add(product.getSupplier());
                }
            }
        }

        Map<String, String[]> selectedCheckboxes = req.getParameterMap();
        List<Supplier> selectedSuppliers = new ArrayList<>();

        for (Supplier supplier : suppliersList) {
            if(selectedCheckboxes.containsKey(supplier.getName())) {
                selectedSuppliers.add(supplier);
            }
        }

        String addedProductIdString = req.getParameter("productId");
        if (!(addedProductIdString == null)) {
            int addedProductId = Integer.parseInt(req.getParameter("productId"));
            Product addedProduct = productService.getProductbyId(addedProductId);
            productService.addToCart(addedProduct);
            selectedSuppliers = suppliersList;
        }

        context.setVariable("suppliersList", suppliersList);
        context.setVariable("categoryName", categoryName);
        context.setVariable("categoryProducts", productDataStore.getAll());
        context.setVariable("filteredSuppliers", selectedSuppliers);
        context.setVariable("numberOfProductsInCart", productService.getProductsAmountFromCart());

        engine.process("product/category.html", context, resp.getWriter());
    }

}
