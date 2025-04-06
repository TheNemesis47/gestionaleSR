package org.gestionale.gestionalesr.resource;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductResponse {
    private Long id;
    private String name;
    private String categoryName;
    private String supplierName;
    private Double salePrice;

    private Map<String, Object> additionalInfo;
}