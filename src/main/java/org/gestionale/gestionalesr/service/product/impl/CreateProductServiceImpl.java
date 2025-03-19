package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.model.Supplier;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.repo.SupplierRepository;
import org.gestionale.gestionalesr.service.product.interfaces.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateProductServiceImpl extends BaseService implements CreateProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SupplierRepository supplierRepository;

    public CreateProductServiceImpl(ProductRepository productRepository,
                                    SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Product createProduct(Product product) {
        logger.info("Creating product with name {}", product.getName());
        Optional<Supplier> supplier = supplierRepository.findById(product.getSupplier().getId());
        if (supplier.isEmpty()){
            logger.warn("Supplier with id {} not found", product.getSupplier().getId());
            return null;
        }
        product.setSupplier(supplier.get());
        return productRepository.save(product);
    }
}
