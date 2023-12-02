package com.chiquetano.springboot.repositories;

import com.chiquetano.springboot.models.ProductModel;
import com.chiquetano.springboot.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//@Repository
public interface SaleRepository extends JpaRepository<SaleModel, UUID> {
}
