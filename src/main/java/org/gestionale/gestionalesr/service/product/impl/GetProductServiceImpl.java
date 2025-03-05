package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.GetAllProductService;
import org.gestionale.gestionalesr.service.product.interfaces.GetProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetProductServiceImpl implements GetProductService {

    @Autowired
    private final ProductRepository productRepository;

    public GetProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product;
    }
}