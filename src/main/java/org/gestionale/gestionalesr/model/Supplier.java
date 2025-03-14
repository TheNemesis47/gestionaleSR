package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @Column(name = "vat_number")
    private String vatNumber;

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
}
