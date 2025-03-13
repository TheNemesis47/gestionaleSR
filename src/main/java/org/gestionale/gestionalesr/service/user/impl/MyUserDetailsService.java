package org.gestionale.gestionalesr.service.user.impl;

import org.gestionale.gestionalesr.model.UserPrincipal;
import org.gestionale.gestionalesr.model.Users;
import org.gestionale.gestionalesr.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepo.findByEmail(email);
        System.out.println("User: " + user);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user);
    }
}