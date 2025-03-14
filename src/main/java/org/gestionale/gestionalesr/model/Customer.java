package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer loyaltyPoints;

    private Boolean isCompany;

    @Column(name = "vat_number")
    private String vatNumber; // Partita IVA

    private String name;

    private String address;

    private String zipCode;

    private String city;

    private String province;

    private String country;

    private String phone;

    private String email;

    private String pec;

    private String notes;

    private LocalDate registrationDate;
}
