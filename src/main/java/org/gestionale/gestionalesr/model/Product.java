package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String sku;
    private String barcode;
    private String category;
    private String brand;
    private Double price;
    private Double discount;
    private Integer stockQuantity;
    private String unit;
    private Boolean isActive;

    @ElementCollection
    private List<String> images;

    @ElementCollection
    private List<String> tags;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Costruttori
    public Product() {}

    public Product(String name, String description, String sku, String barcode, String category, String brand,
                   Double price, Double discount, Integer stockQuantity, String unit, Boolean isActive,
                   List<String> images, List<String> tags, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.barcode = barcode;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.discount = discount;
        this.stockQuantity = stockQuantity;
        this.unit = unit;
        this.isActive = isActive;
        this.images = images;
        this.tags = tags;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}