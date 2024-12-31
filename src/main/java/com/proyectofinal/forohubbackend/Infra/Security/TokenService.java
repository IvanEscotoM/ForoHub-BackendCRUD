package com.proyectofinal.forohubbackend.Infra.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    //Para cuestiones de prueba, la api.secret, esta "hardcodeada" y no en variables de enternos, por lo que se encuentra en
    // application.properties, puedes cambiarlo segun se requiera.
    @Value("${api.security.secret}")
    private String secreto;
    //Para evitar tener que generar nuevos tokens, esta con muchas horas, cambiar a horas mas ideales para un loggin
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(1000).toInstant(ZoneOffset.UTC);

    }

    public String generarToken(Usuario usuario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secreto);
            return JWT.create()
                    .withIssuer("Foro Hub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algoritmo);
        } catch(JWTCreationException e){
            throw new RuntimeException(e); // Login invalido
        }
    }

    public String getSubjet(String token){
        DecodedJWT verificador = null;
        if(token==null){
            throw new RuntimeException("No se encuentra el token");
        }
        try{
            Algorithm algoritmo = Algorithm.HMAC256(secreto);
            verificador = JWT.require(algoritmo)
                    .withIssuer("Foro Hub")
                    .build()
                    .verify(token);
            verificador.getSubject();
        } catch(JWTVerificationException e){
            throw new RuntimeException(e); //no esta empatando el issuer.
        }
        return verificador.getSubject();
    }

}
