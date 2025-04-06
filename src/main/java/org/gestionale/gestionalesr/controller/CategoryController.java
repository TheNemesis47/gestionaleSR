package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Category;
import org.gestionale.gestionalesr.repo.CategoryRepository;
import org.gestionale.gestionalesr.request.CategoryRequest;
import org.gestionale.gestionalesr.service.category.interfaces.CreateCategory;
import org.gestionale.gestionalesr.service.category.interfaces.GetAllCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private final GetAllCategoryService getAllCategoryService;
    @Autowired
    private final CreateCategory createCategory;

    public CategoryController(GetAllCategoryService getAllCategoryService,
                              CreateCategory createCategory) {
        this.getAllCategoryService = getAllCategoryService;
        this.createCategory = createCategory;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategory() {
        List<Category> response = getAllCategoryService.getAllCategories();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{categoryID}")
    public ResponseEntity<Category> getCategory(@PathVariable long categoryID) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest category) {
        Category response = createCategory.createCategory(category);
        return ResponseEntity.ok(response);
    }
}