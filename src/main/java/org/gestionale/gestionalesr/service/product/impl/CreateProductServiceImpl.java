package org.gestionale.gestionalesr.service.product.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.mapper.FromProductRequestToProductMapper;
import org.gestionale.gestionalesr.model.Product;
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
import java.nio.file.Paths;

@Service
public class CreateProductServiceImpl extends BaseService implements CreateProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final SupplierRepository supplierRepository;
    @Autowired
    private final FromProductRequestToProductMapper fromProductRequestToProductMapper;


    public CreateProductServiceImpl(ProductRepository productRepository,
                                    SupplierRepository supplierRepository,
                                    FromProductRequestToProductMapper fromProductRequestToProductMapper) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.fromProductRequestToProductMapper = fromProductRequestToProductMapper;
    }

    @Override
    public Product createProduct(ProductRequest productRequest, List<MultipartFile> images) {
        var product = fromProductRequestToProductMapper.apply(productRequest);

        // Logica custom per il supplier
        var supplierId = productRequest.getSupplierId();
        if (supplierId != null) {
            var supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Supplier non trovato con id: " + supplierId));
            product.setSupplier(supplier);
        }

        // Salva preliminarmente il prodotto per avere eventualmente un id (se necessario)
        product = productRepository.save(product);

        // Recupera l'id dell'employee loggato dal contesto di Spring Security
        var userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var employeeId = userPrincipal.getId();

        // Definisce la directory base dove salvare le immagini (la cartella "negozioImmagini" deve esistere o verrà creata)
        var baseDir = "src/main/resources/negozioImmagini";
        // Costruisce il path per il loggato: negozioImmagini/<employeeId>
        var employeeDirPath = baseDir + File.separator + employeeId;
        var employeeDir = new File(employeeDirPath);
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
                var file = images.get(i);
                var originalFilename = file.getOriginalFilename();
                // Crea un nome file univoco (ad esempio aggiungendo un timestamp)
                var fileName = System.currentTimeMillis() + "_" + originalFilename;
                var filePath = Paths.get(employeeDirPath, fileName);
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
