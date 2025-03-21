package org.gestionale.gestionalesr.mapper;

import org.gestionale.gestionalesr.config.mapstruct.MapStructConfig;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.function.Function;

@Mapper(config = MapStructConfig.class)
public interface FromProductRequestToProductMapper extends Function<ProductRequest, Product> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product apply(ProductRequest productRequest);
}
