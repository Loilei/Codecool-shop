package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cart"})
public class OrderController extends HttpServlet {

  public int productId;

  @Override
  protected void doPost(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException {
    ProductDao productDataStore = ProductDaoMem.getInstance();
    ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
    ProductService productService = new ProductService(productDataStore, productCategoryDataStore);
    SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
    OrderDao orderDataStore = OrderDaoMem.getInstance();

    TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
    WebContext context = new WebContext(req, resp, req.getServletContext());

    String productIdInJson = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    JsonPrimitive jsonPrimitive = new JsonParser().parse(productIdInJson).getAsJsonObject().getAsJsonPrimitive("productId");
    int productId = jsonPrimitive.getAsInt();
//    this.productId = productIdInJson.getInt("productId");


//    System.out.println(gson.fromJson(productIdInJson,String.class));
    System.out.println("Working");

//    Product productToAdd = productDataStore.find(productId);

    ;



  }







}
