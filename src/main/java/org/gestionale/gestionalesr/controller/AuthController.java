package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.model.Shop;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private List<Employee> usersList = new ArrayList<>(
            List.of(
                    new Employee(1L, "prova@mail.com", "password", "Employee", "Mario", "Rossi", "1234567890", new Shop()),
                    new Employee(2L, "prova2@mail.com", "password", "Admin", "Luca", "Bianchi", "0987654321", new Shop())
            ));

    @GetMapping("/users")
    public List<Employee> getUsers() {
        return usersList;
    }
}
