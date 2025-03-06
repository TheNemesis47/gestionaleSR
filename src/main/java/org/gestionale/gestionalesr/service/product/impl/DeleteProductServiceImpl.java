package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.DeleteProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductServiceImpl extends BaseService implements DeleteProductService {
    @Autowired
    private final ProductRepository productRepository;

    public DeleteProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        logger.info("Deleting product with id {}", id);
        boolean exists = productRepository.existsById(id);
        if (exists) {
            logger.info("Product with id {} already exists", id);
            productRepository.deleteById(id);
            return true;
        } else {
            logger.info("Product with id {} does not exist", id);
            return false;
        }
    }
}
