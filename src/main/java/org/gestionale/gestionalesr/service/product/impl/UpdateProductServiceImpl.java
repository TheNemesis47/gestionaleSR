package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductServiceImpl implements UpdateProductService {

    @Autowired
    private final ProductRepository productRepository;

    public UpdateProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product updateProduct(Product product, Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            Product result = productRepository.save(product);
            return product;
        } else {
            return null;
        }
    }
}
