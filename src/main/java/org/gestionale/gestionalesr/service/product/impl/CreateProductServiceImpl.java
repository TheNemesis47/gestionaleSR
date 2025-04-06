package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.*;
import org.gestionale.gestionalesr.repo.CategoryRepository;
import org.gestionale.gestionalesr.repo.EmployeeRepository;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.repo.SupplierRepository;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.gestionale.gestionalesr.resource.ProductResponse;
import org.gestionale.gestionalesr.service.product.interfaces.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CreateProductServiceImpl extends BaseService implements CreateProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final SupplierRepository supplierRepository;

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public CreateProductServiceImpl(ProductRepository productRepository,
                                    SupplierRepository supplierRepository,
                                    EmployeeRepository employeeRepository,
                                    CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest request, List<MultipartFile> images) {

        // ==================== [1. Recupero dati dal DTO] ====================
        Long supplierId = request.getSupplierId();

        // ==================== [2. Recupero dati CATEGORIA] ====================
        String categoryName = request.getCategory();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new RuntimeException("Categoria non trovata con NOME: " + categoryName));

        // ==================== [3. Recupero EMPLOYEE e SHOP] ====================
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = userPrincipal.getId();

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null || employee.getShop() == null) {
            throw new RuntimeException("L'employee loggato non è associato a nessun shop!");
        }

        // ==================== [4. Creazione entità PRODUCT] ====================
        Product product = new Product()
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setCategory(category)
                .setPurchasePrice(request.getPurchasePrice())
                .setSalePrice(request.getSalePrice())
                .setVatRate(request.getVatRate())
                .setBarcode(request.getBarcode())
                .setWeight(request.getWeight())
                .setWidth(request.getWidth())
                .setHeight(request.getHeight())
                .setDepth(request.getDepth())
                .setVolume(request.getVolume())
                .setStockQuantity(request.getStockQuantity())
                .setShop(employee.getShop())
                .setImages(new ArrayList<>());

        if (supplierId != null) {
            Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
            product.setSupplier(supplier);
        }

        // ==================== [5. Salvataggio PRODUCT] ====================
        product = productRepository.save(product);

        // ==================== [6. Gestione IMMAGINI] ====================
        if (images != null && !images.isEmpty()) {
            String baseDir = "src/main/resources/negozioImmagini";
            String employeeDirPath = baseDir + File.separator + employeeId;
            File employeeDir = new File(employeeDirPath);
            if (!employeeDir.exists() && !employeeDir.mkdirs()) {
                throw new RuntimeException("Impossibile creare la cartella per l'employee id " + employeeId);
            }

            for (MultipartFile file : images) {
                try {
                    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(employeeDirPath, fileName);
                    Files.write(filePath, file.getBytes());

                    Images image = new Images()
                            .setImageUrl(filePath.toString())
                            .setProduct(product)
                            .setShop(employee.getShop());

                    product.getImages().add(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // ==================== [7. Costruzione ProductResponse DTO] ====================
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("purchasePrice", product.getPurchasePrice());
        additionalInfo.put("vatRate", product.getVatRate());
        additionalInfo.put("stockQuantity", product.getStockQuantity());
        additionalInfo.put("barcode", product.getBarcode());
        additionalInfo.put("tag", product.getTag());
        additionalInfo.put("volume", product.getVolume());
        additionalInfo.put("description", product.getDescription());
        additionalInfo.put("dimensions", Map.of(
                "weight", product.getWeight(),
                "width", product.getWidth(),
                "height", product.getHeight(),
                "depth", product.getDepth()
        ));
        additionalInfo.put("images", product.getImages().stream()
                .map(Images::getImageUrl)
                .toList());

        return new ProductResponse()
                .setId(product.getId())
                .setName(product.getName())
                .setCategoryName(product.getCategory().getName())
                .setSupplierName(product.getSupplier() != null ? product.getSupplier().getName() : null)
                .setSalePrice(product.getSalePrice())
                .setAdditionalInfo(additionalInfo);
    }
}
