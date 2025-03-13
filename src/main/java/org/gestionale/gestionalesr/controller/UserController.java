package org.gestionale.gestionalesr.controller;

import org.gestionale.gestionalesr.model.Users;
import org.gestionale.gestionalesr.request.LoginRequest;
import org.gestionale.gestionalesr.request.RegisterRequest;
import org.gestionale.gestionalesr.resource.AuthResponse;
import org.gestionale.gestionalesr.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @PostMapping("/register")
    public Users register(@RequestBody RegisterRequest request) {
        return service.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        System.out.println("emaillllll" + request.getEmail());
        return service.verify(request);
    }
}
