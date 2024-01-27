package br.com.jonascamargo.travelsmanager.services.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.jonascamargo.travelsmanager.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
       var token = this.recoverToken(request);
       if(token != null) {
        var login = tokenService.validateToken(token);
        // TA Vindo NULO o USERDETAILS - devo mexer no findByLogin? Ja ta sendo tratado em algum lugar?
        UserDetails userDetails = userRepository.findByLogin(login);
    
        var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // salvando token no contexto da req, para que o spring consiga utilizar depois. Caso nao encontremos usuarios, nada sera salvo e iremos para o proximo filtro
        SecurityContextHolder.getContext().setAuthentication(authentication);

       }
       // ir para o proximo filtro
       filterChain.doFilter(request, response);
    }

    // UserDetails user = userRepository.findByLogin(login);

    // var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    // SecurityContextHolder.getContext().setAuthentication(authentication);

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        // early return, caso nao haja token na req
        if(authHeader == null) return null;
        // retirando o "Bearer" que por padrao vem na nossa identificacao, para pegar apenas o espaco vazio que vem apos
        return authHeader.replace("Bearer ", "");
    }
    
}
