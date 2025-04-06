package org.gestionale.gestionalesr.service.category.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Category;
import org.gestionale.gestionalesr.repo.CategoryRepository;
import org.gestionale.gestionalesr.service.category.interfaces.GetAllCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetAllCategoryServiceImpl extends BaseService implements GetAllCategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public GetAllCategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Get all categories");
        var categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            logger.info("No categories found");
        }
        return categories;
    }
}
