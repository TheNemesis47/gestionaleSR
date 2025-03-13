package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.model.Users;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private List<Employee> usersList = new ArrayList<>(
            List.of(
                    new Employee("prova", "prova", "prova", 1000.0, "prova", "prova"),
                    new Employee("prova2", "prova2", "prova2", 2000.0, "prova2", "prova2")
            ));

    @GetMapping("/users")
    public List<Employee> getUsers() {
        return usersList;
    }
}
