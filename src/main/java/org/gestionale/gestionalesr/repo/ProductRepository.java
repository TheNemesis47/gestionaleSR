package org.gestionale.gestionalesr.repo;

import jakarta.transaction.Transactional;
import org.gestionale.gestionalesr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
