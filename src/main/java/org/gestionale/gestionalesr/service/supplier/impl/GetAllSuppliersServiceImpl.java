package org.gestionale.gestionalesr.service.supplier.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Supplier;
import org.gestionale.gestionalesr.repo.SupplierRepository;
import org.gestionale.gestionalesr.service.supplier.interfaces.GetAllSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllSuppliersServiceImpl extends BaseService implements GetAllSuppliersService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public GetAllSuppliersServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        logger.info("Getting all suppliers");
        List<Supplier> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()) {
            logger.warn("No suppliers found");
            return  null;
        } else {
            logger.info("Found {} suppliers", suppliers.size());
            return suppliers;
        }
    }
}
