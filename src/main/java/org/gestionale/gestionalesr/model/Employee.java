package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@DiscriminatorValue("Employee")
public class Employee extends Users {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Double salary;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Shift> shifts = new ArrayList<>();



    //constructor with all fields and email and password
    public Employee(String firstName, String lastName, String phoneNumber, double salary, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.setEmail(email);
        this.setPassword(password);
        this.setRole("Employee");
    }

    public Employee(){
        super();
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
