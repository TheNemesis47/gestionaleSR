package org.gestionale.gestionalesr.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.gestionale.gestionalesr.service.product.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createProduct(
            @RequestPart(value = "product", required = true) String productJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductRequest productDTO = objectMapper.readValue(productJson, ProductRequest.class);

            Product savedProduct = createProductService.createProduct(productDTO, images);
            if (savedProduct == null) {
                return ResponseEntity.badRequest().body("Errore nella creazione del prodotto");
            }
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore durante la deserializzazione del prodotto: " + e.getMessage());
        }
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
