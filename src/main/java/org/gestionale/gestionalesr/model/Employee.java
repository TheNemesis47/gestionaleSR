package org.gestionale.gestionalesr.model;

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
}

