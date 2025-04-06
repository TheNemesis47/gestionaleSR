package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.model.UserPrincipal;
import org.gestionale.gestionalesr.repo.EmployeeRepository;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.service.product.interfaces.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductServiceImpl extends BaseService implements UpdateProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public UpdateProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product updateProduct(Product product, Long id) {
        logger.info("Update product with id {}", id);
        var exists = productRepository.findById(id);
        if (exists.isPresent()) {
            var existingProduct = exists.get();
            // Recupero dell'employee e shop associato (già presente)
            var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            var employee = employeeRepository.findById(userPrincipal.getId()).orElse(null);
            if (employee == null || employee.getShop() == null) {
                throw new RuntimeException("L'employee loggato non è associato a nessun shop!");
            }
            product.setShop(employee.getShop());

            // Se il supplier nel payload è null, mantieni quello esistente
            if (product.getSupplier() == null) {
                product.setSupplier(existingProduct.getSupplier());
            }

            Product result = productRepository.save(product);
            logger.info("Product with id {} was successfully updated", id);
            return result;
        } else {
            logger.info("Product with id {} not found", id);
            return null;
        }
    }

}
