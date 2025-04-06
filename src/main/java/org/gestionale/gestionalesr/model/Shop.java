package org.gestionale.gestionalesr.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "shops")
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private String piva;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("shop-product") // Gestisce la relazione con Product
    private List<Product> products;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("shop-employee") // Gestisce la relazione con Employee
    private List<Employee> employees;
}
