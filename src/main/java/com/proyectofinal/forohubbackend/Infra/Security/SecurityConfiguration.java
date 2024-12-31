package com.proyectofinal.forohubbackend.Infra.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    //Inyectamos el componente necesario para filtrar exactamente lo que necesitamos obtener
    //que es el JWT Token
    @Autowired
    private SecurityFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Aqui deshabilito una proteccion a un tipo de ataque, porque como estoy creando una API REST, puedo
        //crear una sesion Stateless.
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequest)->
                        authorizeHttpRequest.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                                .anyRequest()
                                .authenticated()
                                )
                                //aqui estoy permitiendo acceder a la documentacion de Spring Doc para que se puedan
                                //acceder a distintos endpoints, mas facil.
                //por ultimo aqui agrego el filtro de login y password(en este caso es nombre usuario
                                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                                .build();



    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //El usuario lo necesitamos crear en una base de datos, inicialmente ya con la constrasena transformada a Bcrypt
    //de esta manera, al mandar en el cuerpo del login la contrasena "normal" puede ser contrastada con la DB.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
