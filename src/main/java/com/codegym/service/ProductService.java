package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;
    @PersistenceContext
    EntityManager en;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void update(Product product) {
        if (product.getProductId() != null) {
            en.merge(product);
        } else {
            en.persist(product);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findOne(id);
        if (product != null) {
            productRepository.delete(id);
        }
    }

    @Override
    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }
}
