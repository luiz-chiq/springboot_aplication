package com.chiquetano.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_SALES")
public class SaleModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sale", updatable = false, nullable = false)
    private UUID idSale;

    @Column(name = "total_sale_value", precision = 38, scale = 2)
    private BigDecimal value = BigDecimal.valueOf(0.0);

    @ManyToMany
    private List<ProductModel> products;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue() {
        for (ProductModel product: products) {
            value = value.add(product.getValue());
        }
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }


    public UUID getIdSale() {
        return idSale;
    }

    public void setIdSale(UUID idProduct) {
        this.idSale = idProduct;
    }
}
