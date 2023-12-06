package com.chiquetano.springboot.services;

import com.chiquetano.springboot.models.ProductModel;
import com.chiquetano.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    ProductModel save(ProductModel productModel);
    List<ProductModel> findAll();
    Optional<ProductModel> findOne(UUID id);
    ProductModel update(ProductModel productModel);
    void delete(UUID id);

}
