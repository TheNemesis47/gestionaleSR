package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.mapper.FromProductRequestToProductMapper;
import org.gestionale.gestionalesr.mapper.FromProductToProductResponseMapper;
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
    FromProductRequestToProductMapper fromProductRequestToProductMapper;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SupplierRepository supplierRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final FromProductToProductResponseMapper responseMapper;

    public CreateProductServiceImpl(ProductRepository productRepository,
                                    SupplierRepository supplierRepository,
                                    EmployeeRepository employeeRepository,
                                    CategoryRepository categoryRepository,
                                    FromProductToProductResponseMapper responseMapper) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
        this.categoryRepository = categoryRepository;
        this.responseMapper = responseMapper;
    }


    @Override
    public ProductResponse createProduct(ProductRequest request, List<MultipartFile> images) {

        // ==================== [1. Recupero dati dal DTO] ====================
        Long supplierId = request.getSupplierId();

        // ==================== [3. Recupero EMPLOYEE e SHOP] ====================
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = userPrincipal.getId();

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null || employee.getShop() == null) {
            throw new RuntimeException("L'employee loggato non è associato a nessun shop!");
        }

        // ==================== [4. Map entità PRODUCT] ====================
        var product = fromProductRequestToProductMapper.apply(request);
        product.setShop(employee.getShop());
        product.setImages(new ArrayList<>());

        if (supplierId != null) {
            Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
            product.setSupplier(supplier);
        }

        // ==================== [2. Recupero dati CATEGORIA] ====================
        String categoryName = request.getCategory();
        Category category = categoryRepository.findByName(product.getCategory().getName())
                .orElseThrow(() -> new RuntimeException("Categoria non trovata"));
        product.setCategory(category);

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
        return responseMapper.apply(product);
    }
}
