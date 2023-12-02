package com.chiquetano.springboot.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", updatable = false, nullable = false)
    private UUID idProduct;

    @Column(name = "product_value", precision = 38, scale = 2)
    private BigDecimal value;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "category")
    private Category category;


    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(String category) {
        for (Category cat : Category.values()) {
            if (cat.name().equalsIgnoreCase(category)) {
                this.category = Category.valueOf(category.toUpperCase());
                return;
            }
        }
        this.category = Category.OTHER;
    }
}
