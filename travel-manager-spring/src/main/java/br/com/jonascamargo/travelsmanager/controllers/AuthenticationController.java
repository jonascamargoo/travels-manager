package br.com.jonascamargo.travelsmanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.travelsmanager.domain.dtos.AuthenticationDTO;
import br.com.jonascamargo.travelsmanager.domain.dtos.LoginResponseDTO;
import br.com.jonascamargo.travelsmanager.domain.dtos.RegisterDTO;
import br.com.jonascamargo.travelsmanager.domain.models.User;
import br.com.jonascamargo.travelsmanager.repositories.UserRepository;
import br.com.jonascamargo.travelsmanager.services.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;

    // RETIRAR O USER REPOSITORY DAQUI DEPOIS

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    // Quando o usuario logar, ele recebera um token e com esse token conseguira acessar os endpoints definidos em SecurityConfiguration
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        // fazendo encode e comparacao
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User)auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    // nao uso o mesmo DTO, pois aqui tera o Role do user tb
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.userRepository.findByLogin(registerDTO.password()) != null) {
            return ResponseEntity.badRequest().build();
        }
        // pegando o hash da senha
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        
        User newUser = new User(registerDTO.login(), encryptedPassword, registerDTO.userRole());

        this.userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    
}
