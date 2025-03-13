package org.gestionale.gestionalesr;

import jakarta.transaction.Transactional;
import org.gestionale.gestionalesr.model.Employee;
import org.gestionale.gestionalesr.model.Users;
import org.gestionale.gestionalesr.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        // Creiamo un Employee di prova
        Employee employee = new Employee();
        employee.setEmail("dipendente@example.com");
        employee.setPassword("password123");
        employee.setRole("Employee");
        employee.setFirstName("Mario");
        employee.setLastName("Rossi");
        // (Opzionale) Imposta anche phoneNumber, salary, ecc.

        // Salviamo l'Employee nel DB
        userRepository.save(employee);

        // Recuperiamo l'utente tramite il repository
        Users foundUser = userRepository.findByEmail("dipendente@example.com");

        // Verifichiamo che l'utente sia stato trovato correttamente
        assertNotNull(foundUser, "L'utente non dovrebbe essere null");
        assertEquals("dipendente@example.com", foundUser.getEmail(), "L'email ritornata non corrisponde a quella inserita");
        assertTrue(foundUser instanceof Employee, "Il tipo ritornato dovrebbe essere un Employee");
    }
}
