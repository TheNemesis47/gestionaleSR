package org.gestionale.gestionalesr.service.category.interfaces;

import org.gestionale.gestionalesr.model.Category;
import org.gestionale.gestionalesr.request.CategoryRequest;

public interface CreateCategory {
    Category createCategory(CategoryRequest categoryRequest);
}
