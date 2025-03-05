package org.gestionale.gestionalesr.service.product.interfaces;

import org.gestionale.gestionalesr.model.Product;

import java.util.Optional;

public interface GetProductService {
    Optional<Product> getProduct(Long id);
}
