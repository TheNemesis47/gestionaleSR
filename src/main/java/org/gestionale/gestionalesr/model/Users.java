package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
public abstract class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }
}

