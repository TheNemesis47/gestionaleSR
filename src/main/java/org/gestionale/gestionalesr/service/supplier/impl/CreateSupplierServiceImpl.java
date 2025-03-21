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
        Boolean isPresent = isSupplierPresent(supplier);

        // Se è già presente, blocchiamo
        if (isPresent) {
            logger.error("Supplier with VAT number {} already exists", supplier.getPiva());
            return null;
        } else {
            // Altrimenti salviamo
            logger.info("Creating supplier with VAT number {}", supplier.getPiva());
            return supplierRepository.save(supplier);
        }
    }

    private Boolean isSupplierPresent(Supplier supplier) {
        String piva = supplier.getPiva();
        String companyName = supplier.getName();
        Supplier existingSupplierByPiva = supplierRepository.findByPiva(piva);
        Supplier existingSupplierByName = supplierRepository.findByName(companyName);

        if ((existingSupplierByPiva != null && piva.equals(existingSupplierByPiva.getPiva())) ||
            (existingSupplierByName != null && companyName.equals(existingSupplierByName.getName()))) {
            logger.error("Supplier with VAT number {} or company name {} already exists", piva, companyName);
            return true;
        } else {
            logger.info("Creating supplier with VAT number {} and company name {}", piva, companyName);
            return false;
        }
    }
}
