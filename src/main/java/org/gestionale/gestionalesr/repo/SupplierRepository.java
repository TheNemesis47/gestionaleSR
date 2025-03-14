package org.gestionale.gestionalesr.repo;

import org.gestionale.gestionalesr.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByName(String name);

    Supplier findByEmail(String email);

    Supplier findByPhone(String phone);

    Supplier findByPiva(String piva);
}
