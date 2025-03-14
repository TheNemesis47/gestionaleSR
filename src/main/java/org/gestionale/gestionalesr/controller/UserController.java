package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.request.LoginRequest;
import org.gestionale.gestionalesr.request.RegisterRequest;
import org.gestionale.gestionalesr.resource.AuthResponse;
import org.gestionale.gestionalesr.service.user.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private EmployeeServiceImpl service;

    @PostMapping("/register")
    public Employee register(@RequestBody Employee request) {
        System.out.println(request.getEmail() + " " + request.getPassword());
        return service.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        System.out.println("emaillllll" + request.getEmail());
        return service.verify(request);
    }
}
