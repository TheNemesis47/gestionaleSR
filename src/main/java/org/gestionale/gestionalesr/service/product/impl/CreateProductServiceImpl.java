package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.model.Product;
import org.gestionale.gestionalesr.model.Supplier;
import org.gestionale.gestionalesr.model.UserPrincipal;
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

    public CreateProductServiceImpl(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Product createProduct(ProductRequest productDTO, List<MultipartFile> images) {
        Long supplierId = productDTO.getSupplierId();

        // Converte il DTO in un'entità Product
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

        // Se viene passato un supplierId, recupera il fornitore e lo imposta
        if (supplierId != null) {
            Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
            product.setSupplier(supplier);
        }

        // Salva preliminarmente il prodotto per avere eventualmente un id (se necessario)
        product = productRepository.save(product);

        // Recupera l'id dell'employee loggato dal contesto di Spring Security
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long employeeId = userPrincipal.getId();

        // Definisce la directory base dove salvare le immagini (la cartella "negozioImmagini" deve esistere o verrà creata)
        String baseDir = "src/main/resources/negozioImmagini";
        // Costruisce il path per il loggato: negozioImmagini/<employeeId>
        String employeeDirPath = baseDir + File.separator + employeeId;
        File employeeDir = new File(employeeDirPath);
        if (!employeeDir.exists()) {
            boolean created = employeeDir.mkdirs();
            if (!created) {
                // Se non riesci a creare la cartella, puoi decidere di lanciare un'eccezione
                throw new RuntimeException("Impossibile creare la cartella per l'employee id " + employeeId);
            }
        }

        // Gestione del salvataggio delle immagini
        if (images != null && !images.isEmpty()) {
            for (int i = 0; i < images.size(); i++) {
                MultipartFile file = images.get(i);
                String originalFilename = file.getOriginalFilename();
                // Crea un nome file univoco (ad esempio aggiungendo un timestamp)
                String fileName = System.currentTimeMillis() + "_" + originalFilename;
                Path filePath = Paths.get(employeeDirPath, fileName);
                try {
                    Files.write(filePath, file.getBytes());
                    // Per esempio, se vuoi salvare il percorso della prima immagine nel campo imageUrl
                    if (i == 0) {
                        product.setImageUrl(filePath.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Gestisci l'errore: qui puoi decidere di lanciare un'eccezione o continuare
                }
            }
        }

        // Salva il prodotto aggiornato (con eventuale imageUrl) e restituisce l'entità
        return productRepository.save(product);
    }
}
