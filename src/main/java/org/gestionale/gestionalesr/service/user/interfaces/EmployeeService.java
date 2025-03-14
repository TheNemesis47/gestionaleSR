package org.gestionale.gestionalesr.service.user.interfaces;

import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.request.LoginRequest;
import org.gestionale.gestionalesr.request.RegisterRequest;
import org.gestionale.gestionalesr.resource.AuthResponse;

public interface EmployeeService {
    Employee registerUser(Employee request);
    AuthResponse verify(LoginRequest request);
}
