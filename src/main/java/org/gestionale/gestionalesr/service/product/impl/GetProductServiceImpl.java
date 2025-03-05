package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.GetProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductServiceImpl extends BaseService implements GetProductService {

    @Autowired
    private final ProductRepository productRepository;

    public GetProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        logger.info("Get product with id {}", id);
        Optional<Product> product = productRepository.findById(id);
        return product;
    }
}