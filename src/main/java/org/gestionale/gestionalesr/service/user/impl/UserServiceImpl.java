package org.gestionale.gestionalesr.service.user.impl;

import org.gestionale.gestionalesr.config.service.BaseService;
import org.gestionale.gestionalesr.exception.Gestionale404Exception;
import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.model.Users;
import org.gestionale.gestionalesr.repo.UserRepository;
import org.gestionale.gestionalesr.request.LoginRequest;
import org.gestionale.gestionalesr.request.RegisterRequest;
import org.gestionale.gestionalesr.resource.AuthResponse;
import org.gestionale.gestionalesr.service.user.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepository repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public Users registerUser(RegisterRequest request) {
        Employee employee = request.getEmployee();
        String email = employee.getEmail();
        Users user = repo.findByEmail(employee.getEmail());
        if (user != null && email.equals(user.getEmail())) {
            throw new Gestionale404Exception("User already exists");
        }
        employee.setPassword(encoder.encode(employee.getPassword()));
        return repo.save(employee);
    }

    @Override
    public AuthResponse verify(LoginRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            var user = repo.findByEmail(request.getEmail());
            String token = jwtService.generateToken(user.getEmail());
            return new AuthResponse(token, user.getEmail(), user.getRole());
        } else {
            throw new Gestionale404Exception("Authentication failed");
        }
    }
}
