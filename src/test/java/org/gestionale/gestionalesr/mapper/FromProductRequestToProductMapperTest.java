package org.gestionale.gestionalesr.mapper;

import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class FromProductRequestToProductMapperTest {

    private final FromProductRequestToProductMapper mapper =
            Mappers.getMapper(FromProductRequestToProductMapper.class);

    @Test
    void apply_shouldMapCorrectly() {
        ProductRequest request = new ProductRequest();
        request.setName("Product A");
        request.setDescription("Description");
        request.setCategory("Category");
        request.setSubcategory("Subcategory");
        request.setPurchasePrice(10.0);
        request.setSalePrice(15.0);
        request.setVatRate(22.0);
        request.setBarcode("123456789");
        request.setWeight(1.5);
        request.setWidth(2.0);
        request.setHeight(3.0);
        request.setDepth(4.0);
        request.setVolume(24.0);
        request.setStockQuantity(100);
        request.setSupplierId(5L); // deve essere ignorato

        Product product = mapper.apply(request);

        assertNotNull(product);
        assertNull(product.getId());
        assertNull(product.getSupplier());
        assertNull(product.getCreatedAt());
        assertNull(product.getUpdatedAt());

        assertEquals("Product A", product.getName());
        assertEquals("Description", product.getDescription());
        assertEquals("Category", product.getCategory());
        assertEquals("Subcategory", product.getSubcategory());
        assertEquals(10.0, product.getPurchasePrice());
        assertEquals(15.0, product.getSalePrice());
        assertEquals(22.0, product.getVatRate());
        assertEquals("123456789", product.getBarcode());
        assertEquals(1.5, product.getWeight());
        assertEquals(2.0, product.getWidth());
        assertEquals(3.0, product.getHeight());
        assertEquals(4.0, product.getDepth());
        assertEquals(24.0, product.getVolume());
        assertEquals(100, product.getStockQuantity());
    }
}