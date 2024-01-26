package br.com.jonascamargo.travelsmanager.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jonascamargo.travelsmanager.repositories.UserRepository;

@Service    // ao implementar o UserDatailsService o spring security ja detecta a classe
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // public AuthorizationService(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
    
}