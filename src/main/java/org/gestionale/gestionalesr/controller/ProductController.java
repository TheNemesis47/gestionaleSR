package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.service.product.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final CreateProductService createProductService;
    @Autowired
    private final DeleteProductService deleteProductService;
    @Autowired
    private final UpdateProductService updateProductService;
    @Autowired
    private final GetProductService getProductService;
    @Autowired
    private final GetAllProductService getAllProductService;


    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService, UpdateProductService updateProductService, GetProductService getProductService, GetAllProductService getAllProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.getProductService = getProductService;
        this.getAllProductService = getAllProductService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProduct() {
        List<Product> response = getAllProductService.getAllProduct();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Optional<Product> response = getProductService.getProduct(productId);
        return response.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product response = createProductService.createProduct(product);
        return response == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(response);
    }

    //modifica prodotto
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        Product response = updateProductService.updateProduct(product, productId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(response);
        }
    }

    //elimina prodotto
    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long productId) {
        Boolean response = deleteProductService.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }
}
