package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Supplier;
import org.gestionale.gestionalesr.service.supplier.interfaces.CreateSupplierService;
import org.gestionale.gestionalesr.service.supplier.interfaces.GetAllSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private final GetAllSuppliersService getAllSupplierServiceService;
    @Autowired
    private final CreateSupplierService createSupplierServiceService;

    public SupplierController(GetAllSuppliersService getAllSupplierServiceService,
                              CreateSupplierService createSupplierServiceService) {
        this.getAllSupplierServiceService = getAllSupplierServiceService;
        this.createSupplierServiceService = createSupplierServiceService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        var response = getAllSupplierServiceService.getAllSuppliers();
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        var response = createSupplierServiceService.createSupplier(supplier);
        return ResponseEntity.ok(response);
    }
}
