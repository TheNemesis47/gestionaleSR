package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.*;
import org.gestionale.gestionalesr.repo.EmployeeRepository;
import org.gestionale.gestionalesr.repo.ProductRepository;
import org.gestionale.gestionalesr.repo.SupplierRepository;
import org.gestionale.gestionalesr.request.ProductRequest;
import org.gestionale.gestionalesr.service.product.interfaces.CreateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CreateProductServiceImpl extends BaseService implements CreateProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SupplierRepository supplierRepository;
    @Autowired
    private final EmployeeRepository employeeRepository;

    public CreateProductServiceImpl(ProductRepository productRepository,
                                    SupplierRepository supplierRepository,
                                    EmployeeRepository employeeRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Product createProduct(ProductRequest productDTO, List<MultipartFile> images) {
        Long supplierId = productDTO.getSupplierId();

        // Creazione del prodotto
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setSubcategory(productDTO.getSubcategory());
        product.setPurchasePrice(productDTO.getPurchasePrice());
        product.setSalePrice(productDTO.getSalePrice());
        product.setVatRate(productDTO.getVatRate());
        product.setBarcode(productDTO.getBarcode());
        product.setWeight(productDTO.getWeight());
        product.setWidth(productDTO.getWidth());
        product.setHeight(productDTO.getHeight());
        product.setDepth(productDTO.getDepth());
        product.setVolume(productDTO.getVolume());
        product.setStockQuantity(productDTO.getStockQuantity());

        // Recupera l'Employee loggato e lo Shop associato
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = userPrincipal.getId();
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null || employee.getShop() == null) {
            throw new RuntimeException("L'employee loggato non è associato a nessun shop!");
        }

        product.setShop(employee.getShop());

        // Se viene passato un supplierId, recupera il fornitore e lo imposta
        if (supplierId != null) {
            Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
            product.setSupplier(supplier);
        }

        // ✅ Salva il prodotto SOLO DOPO aver settato il negozio
        product = productRepository.save(product);

        // Gestione delle immagini
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

                    // Salva il percorso dell'immagine nel database
                    Images image = new Images();
                    image.setImageUrl(filePath.toString());
                    image.setProduct(product);
                    image.setShop(employee.getShop());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return productRepository.save(product);
    }
}
