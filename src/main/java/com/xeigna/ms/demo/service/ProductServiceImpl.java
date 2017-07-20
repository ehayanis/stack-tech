package com.xeigna.ms.demo.service;

import com.xeigna.ms.demo.model.Product;
import com.xeigna.ms.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public Product findById(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public boolean isProductExist(Product product) {
        return findById(product.getId()) != null;
    }

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        saveProduct(product);
    }

    @Override
    public void deleteProductById(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAllProduct() {
        repository.deleteAll();
    }

}
