package org.gestionale.gestionalesr.service.product.interfaces;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.resource.ProductResponse;

import java.util.List;

public interface GetAllProductService {
    List<ProductResponse> getAllProduct();
}
