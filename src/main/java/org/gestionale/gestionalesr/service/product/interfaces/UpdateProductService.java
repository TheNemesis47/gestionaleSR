package org.gestionale.gestionalesr.service.product.interfaces;

import org.gestionale.gestionalesr.model.Product;

public interface UpdateProductService {
    Product updateProduct(Product product, Long id);
}
