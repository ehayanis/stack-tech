package com.xeigna.ms.demo.service;

import com.xeigna.ms.demo.model.Product;

import java.util.List;

public interface ProductService {

    Product findById(String id);

    List<Product> findAllProducts();

    public boolean isProductExist(Product product);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProductById(String id);

    void deleteAllProduct();
}
