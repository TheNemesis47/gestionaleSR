package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.GetAllProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductServiceImpl implements GetAllProductService {

    @Autowired
    private final ProductRepository productRepository;
    public GetAllProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProduct() {
        List<Product> result = productRepository.findAll();
        return result;
    }
}
