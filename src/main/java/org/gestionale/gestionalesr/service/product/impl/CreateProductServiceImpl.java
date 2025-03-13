package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateProductServiceImpl extends BaseService implements CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating product with name {}", product.getName());
        return productRepository.save(product);
    }
}
