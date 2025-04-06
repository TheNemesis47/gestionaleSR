package org.gestionale.gestionalesr.mapper;

import org.gestionale.gestionalesr.config.mapstruct.MapStructConfig;
import org.gestionale.gestionalesr.model.Images;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.resource.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Mapper(config = MapStructConfig.class)
public interface FromProductToProductResponseMapper extends Function<Product, ProductResponse> {

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "additionalInfo", source = "product", qualifiedByName = "buildAdditionalInfo")
    @Override
    ProductResponse apply(Product product);

    @Named("buildAdditionalInfo")
    default Map<String, Object> buildAdditionalInfo(Product product) {
        Map<String, Object> map = new HashMap<>();

        map.put("description", product.getDescription());
        map.put("barcode", product.getBarcode());
        map.put("purchasePrice", product.getPurchasePrice());
        map.put("vatRate", product.getVatRate());
        map.put("stockQuantity", product.getStockQuantity());
        map.put("tag", product.getTag());
        map.put("volume", product.getVolume());

        map.put("dimensions", Map.of(
                "weight", product.getWeight(),
                "width", product.getWidth(),
                "height", product.getHeight(),
                "depth", product.getDepth()
        ));

        map.put("images", product.getImages() != null
                ? product.getImages().stream().map(Images::getImageUrl).toList()
                : List.of());

        map.put("categoryDescription", product.getCategory() != null
                ? product.getCategory().getDescription()
                : null);

        map.put("createdAt", product.getCreatedAt());
        map.put("updatedAt", product.getUpdatedAt());

        return map;
    }
}
