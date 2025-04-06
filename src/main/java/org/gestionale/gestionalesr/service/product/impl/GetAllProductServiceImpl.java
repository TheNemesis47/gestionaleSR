package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.mapper.FromProductToProductResponseMapper;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.resource.ProductResponse;
import org.gestionale.gestionalesr.service.product.interfaces.GetAllProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductServiceImpl extends BaseService implements GetAllProductService {

    private final ProductRepository productRepository;
    private final FromProductToProductResponseMapper responseMapper;

    @Autowired
    public GetAllProductServiceImpl(ProductRepository productRepository,
                                    FromProductToProductResponseMapper responseMapper) {
        this.productRepository = productRepository;
        this.responseMapper = responseMapper;
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        logger.info("Get all products");

        var result = productRepository.findAll();
        if (result.isEmpty()) {
            logger.warn("No products found");
            return List.of();
        }

        logger.info("Found {} products", result.size());
        return result.stream().map(responseMapper).toList();
    }
}


