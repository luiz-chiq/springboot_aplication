package com.chiquetano.springboot.controllers;

import com.chiquetano.springboot.dtos.SaleRecordDto;
import com.chiquetano.springboot.models.ProductModel;
import com.chiquetano.springboot.models.SaleModel;
import com.chiquetano.springboot.repositories.ProductRepository;
import com.chiquetano.springboot.repositories.SaleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/sales")
    public ResponseEntity<SaleModel> saveSale(@RequestBody @Valid SaleRecordDto saleRecordDto) {
        var saleModel = new SaleModel();
        BeanUtils.copyProperties(saleRecordDto, saleModel);
//        List<ProductModel> products = productRepository.findAllById(saleRecordDto.products());
        List<UUID> productIds = saleRecordDto.products();
        List<ProductModel> products = new ArrayList<>();

        for (UUID productId : productIds) {
            List<ProductModel> productsWithSameId = productRepository.findAllById(Collections.singleton(productId));
            products.addAll(productsWithSameId);
        }
        saleModel.setProducts(products);
        saleModel.setValue();
        return ResponseEntity.status(HttpStatus.CREATED).body(saleRepository.save(saleModel));
    }
}
