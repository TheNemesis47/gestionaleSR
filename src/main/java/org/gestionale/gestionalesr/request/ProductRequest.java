package org.gestionale.gestionalesr.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private String subcategory;
    private Double purchasePrice;
    private Double salePrice;
    private Double vatRate;
    private String barcode;
    private Double weight;
    private Double width;
    private Double height;
    private Double depth;
    private Double volume;
    private Integer stockQuantity;
    private Long supplierId;
    private String imageUrl;
    private String tag;
}