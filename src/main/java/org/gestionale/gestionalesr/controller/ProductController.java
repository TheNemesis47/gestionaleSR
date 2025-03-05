package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping
    public List<Product> getProducts() {
        return null;
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return null;
    }

    //modifica prodotto
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return null;
    }

    //elimina prodotto
    @DeleteMapping
    public void deleteProduct(@RequestBody Product product) {
    }

    //Filtra prodotti per nome, categoria, prezzo
    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam Long productId) {
        return null;
    }

    //Aggiorna Stock Prodotto
    @PostMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        return null;
    }


}
