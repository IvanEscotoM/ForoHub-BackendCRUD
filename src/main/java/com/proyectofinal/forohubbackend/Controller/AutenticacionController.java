package com.proyectofinal.forohubbackend.Controller;


import com.proyectofinal.forohubbackend.Domain.Usuario.DatosUsuarioAuth;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import com.proyectofinal.forohubbackend.Infra.Security.JwtToken;
import com.proyectofinal.forohubbackend.Infra.Security.TokenService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    //Esto es el proceso de login, por ser una API StateLess
    @PostMapping
    public ResponseEntity auntenticarUsuario(@RequestBody @Valid DatosUsuarioAuth datosUsuarioAuth){
        Authentication authToken= new UsernamePasswordAuthenticationToken(datosUsuarioAuth.login(),datosUsuarioAuth.password());
        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JwtToken(jwtToken));
    }

}
