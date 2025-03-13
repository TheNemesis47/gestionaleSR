package org.gestionale.gestionalesr.service.user.interfaces;

import org.gestionale.gestionalesr.model.Users;
import org.gestionale.gestionalesr.request.LoginRequest;
import org.gestionale.gestionalesr.request.RegisterRequest;
import org.gestionale.gestionalesr.resource.AuthResponse;

public interface UserService {
    Users registerUser(RegisterRequest request);
    AuthResponse verify(LoginRequest request);
}
