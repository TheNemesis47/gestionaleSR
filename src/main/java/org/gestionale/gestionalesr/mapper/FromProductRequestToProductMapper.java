package org.gestionale.gestionalesr.mapper;

import org.gestionale.gestionalesr.config.mapstruct.MapStructConfig;
import org.gestionale.gestionalesr.model.Category;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;

@Mapper(config = MapStructConfig.class, uses = {CategoryMapper.class})
public interface FromProductRequestToProductMapper extends Function<ProductRequest, Product> {

    @Mapping(target = "category", source = "category", qualifiedByName = "nameToCategory")
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "shop", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Override
    Product apply(ProductRequest productRequest);
}

