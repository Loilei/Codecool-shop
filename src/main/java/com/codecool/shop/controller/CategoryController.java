package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

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


        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/category.html", context, resp.getWriter());
    }

    @Override
    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)	throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

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



        context.setVariable("suppliersList", suppliersList);
        context.setVariable("categoryName", categoryName);
        //todo
        context.setVariable("categoryProducts", productDataStore.getAll());
        context.setVariable("filteredSuppliers", selectedSuppliers);

        engine.process("product/category.html", context, resp.getWriter());
    }

}
