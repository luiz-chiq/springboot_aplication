package com.chiquetano.springboot.services;

import com.chiquetano.springboot.models.ProductModel;
import com.chiquetano.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductModel> findOne(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(ProductModel productModel) {
        productRepository.delete(productModel);
    }
}
