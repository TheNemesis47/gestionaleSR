package org.gestionale.gestionalesr.service.category.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Category;
import org.gestionale.gestionalesr.repo.CategoryRepository;
import org.gestionale.gestionalesr.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategory extends BaseService implements org.gestionale.gestionalesr.service.category.interfaces.CreateCategory {

    @Autowired
    private final CategoryRepository categoryRepository;

    public CreateCategory(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        categoryRepository.findByName(categoryRequest.getCategory())
                .ifPresent(existing -> {
                    throw new RuntimeException("Categoria con nome \"" + categoryRequest.getCategory() + "\" esiste già!");
                });

        Category category = new Category();
        category.setName(categoryRequest.getCategory());
        category.setDescription(categoryRequest.getDescription());

        return categoryRepository.save(category);
    }

}

