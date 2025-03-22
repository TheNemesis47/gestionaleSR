package org.gestionale.gestionalesr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference("product-images") // Definisce la relazione inversa con Product
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
}
