package br.com.jonascamargo.travelsmanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonascamargo.travelsmanager.domain.dtos.AuthenticationRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.dtos.LoginResponseRecordDTO;
import br.com.jonascamargo.travelsmanager.domain.dtos.RegisterRecordDTO;
import br.com.jonascamargo.travelsmanager.services.security.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

  
    @PostMapping("/login")
    public ResponseEntity<LoginResponseRecordDTO> login(@RequestBody @Valid AuthenticationRecordDTO authenticationRecordDTO) {
        String token = this.authenticationService.loginUser(authenticationRecordDTO);
        var loginResponseRecordDTO = new LoginResponseRecordDTO(token);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseRecordDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRecordDTO registerRecordDTO) {
        this.authenticationService.registerUser(registerRecordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    
    
}
