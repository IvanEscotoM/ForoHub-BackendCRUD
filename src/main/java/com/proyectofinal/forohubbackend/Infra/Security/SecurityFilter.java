package com.proyectofinal.forohubbackend.Infra.Security;

import com.proyectofinal.forohubbackend.Domain.Usuario.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Este es un filtro personalizado que usaremos en la capa de configuracion, para alterar un poco
//el proceso "natural" del filtro web del propio Spring

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    //Aqui es donde personalizamos ese filtro en cada requisicion HTTP

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            authHeader= authHeader.replace("Bearer ", "");
            String sujeto= tokenService.getSubjet(authHeader);
            if(sujeto != null){
                System.out.println("Entra aqui");
                request.setAttribute("username", sujeto);
                UserDetails usuario = usuarioRepositorio.findByLogin(sujeto);
                //Aqui " se fuerza el login en cada request.
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));

            }
        }
        //
        filterChain.doFilter(request, response);
        System.out.println("Termina aqui");
    }
}
