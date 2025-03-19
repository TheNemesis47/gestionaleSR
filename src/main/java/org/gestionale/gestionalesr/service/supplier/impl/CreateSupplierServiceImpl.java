package org.gestionale.gestionalesr.service.supplier.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Supplier;
import org.gestionale.gestionalesr.repo.SupplierRepository;
import org.gestionale.gestionalesr.service.supplier.interfaces.CreateSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSupplierServiceImpl extends BaseService implements CreateSupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public CreateSupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        String piva = supplier.getPiva();
        Supplier existingSupplier = supplierRepository.findByPiva(piva);
        if (existingSupplier != null && piva.equals(existingSupplier.getPiva())){
            logger.error("Supplier with VAT number {} already exists", piva);
            return null;
        } else {
            logger.info("Creating supplier with VAT number {}", piva);
            return supplierRepository.save(supplier);
        }
    }
}
