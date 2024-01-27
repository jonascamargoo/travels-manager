package br.com.jonascamargo.travelsmanager.services.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jonascamargo.travelsmanager.domain.dtos.AuthenticationRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.dtos.RegisterRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.models.User;
import br.com.jonascamargo.travelsmanager.exceptions.customExceptions.UserAlreadyRegisteredException;
import br.com.jonascamargo.travelsmanager.repositories.UserRepository;

@Service
public class AuthenticationService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String loginUser(AuthenticationRecordDTO authenticationRecordDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRecordDTO.login(), authenticationRecordDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        return tokenService.generateToken((User)auth.getPrincipal());
        
    }

    // subsequently validate at database level
    public void registerUser(RegisterRecordDTO registerRecordDTO) {
        if(this.userRepository.findByLogin(registerRecordDTO.login()) != null) {
            throw new UserAlreadyRegisteredException();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRecordDTO.password());
        User newUser = new User(registerRecordDTO.login(), encryptedPassword, registerRecordDTO.userRole());
        this.userRepository.save(newUser);

    }


}