package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductServiceImpl extends BaseService implements UpdateProductService {

    @Autowired
    private final ProductRepository productRepository;

    public UpdateProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product updateProduct(Product product, Long id) {
        logger.info("Update product with id {}", id);
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            Product result = productRepository.save(product);
            logger.info("Product with id {} was successfully updated", id);
            return product;
        } else {
            logger.info("Product with id {} already exists", id);
            return null;
        }
    }
}
