package org.gestionale.gestionalesr.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonBackReference("shop-employee") // Evita il ciclo infinito con Shop
    private Shop shop;
}
