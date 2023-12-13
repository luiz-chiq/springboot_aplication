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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductRepository productRepository;

    @PostMapping
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

    @GetMapping
    public ResponseEntity<List<SaleModel>> getAllSales(){
        List<SaleModel> salessList = saleRepository.findAll();
        if(!salessList.isEmpty()){
            for (SaleModel product: salessList) {
                UUID id = product.getIdSale();
                product.add(linkTo(methodOn(SaleController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(salessList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id){
        Optional<SaleModel> sale = saleRepository.findById(id);
        if(sale.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sale not found.");
        }
        sale.get().add(linkTo(methodOn(SaleController.class).getAllSales()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(sale.get());
    }
}
