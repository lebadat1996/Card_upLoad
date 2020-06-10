package com.codegym.service;

import com.codegym.model.Product;

public interface IProductService {
    Iterable<Product> findAll();

    Product findOne(Long id);

    void update(Product product);

    void remove(Long id);

    Product save(Product product);
}
