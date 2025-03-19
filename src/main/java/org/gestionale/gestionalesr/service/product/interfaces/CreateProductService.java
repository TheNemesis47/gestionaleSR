package org.gestionale.gestionalesr.service.product.interfaces;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;

public interface CreateProductService {
    Product createProduct(ProductRequest product, List<MultipartFile> images);
}
