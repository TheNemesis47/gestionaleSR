package org.gestionale.gestionalesr.mapper;

import org.gestionale.gestionalesr.model.Category;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    /**
     * Mapping da entity a stringa (per response)
     */
    @Named("categoryToName")
    public String toName(Category category) {
        return category != null ? category.getName() : null;
    }

    /**
     * Mapping da nome a entity (solo per costruttori temporanei, non salvi nel DB)
     */
    @Named("nameToCategory")
    public Category fromName(String name) {
        if (name == null || name.isBlank()) return null;
        return Category.builder().name(name).build();
    }
}
