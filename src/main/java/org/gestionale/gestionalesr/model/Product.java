package org.gestionale.gestionalesr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category;
    private String subcategory;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sale_price")
    private Double salePrice;

    private Double vatRate;
    private String barcode;

    private Double weight;
    private Double width;
    private Double height;
    private Double depth;
    private Double volume;
    private String tag;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference("shop-product") // Evita il ciclo infinito con Shop
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonBackReference("supplier-product") // Evita il ciclo infinito con Supplier
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-images") // Gestisce la relazione con Images
    private List<Images> images;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
