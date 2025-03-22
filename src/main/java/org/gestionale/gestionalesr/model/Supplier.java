package org.gestionale.gestionalesr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "p_iva", nullable = false, unique = true)
    private String piva;

    private String name;
    private String address;
    private String zipCode;
    private String city;
    private String province;
    private String country;
    private String contactPerson;
    private String phone;
    private String email;
    private String pec;
    private String notes;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("supplier-product") // Gestisce la relazione con Product
    private List<Product> products;
}
