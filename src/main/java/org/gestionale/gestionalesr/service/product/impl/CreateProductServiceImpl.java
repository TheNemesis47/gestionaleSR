package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductServiceImpl implements CreateProductService {

    @Autowired
    private final ProductRepository productRepository;

    public CreateProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
