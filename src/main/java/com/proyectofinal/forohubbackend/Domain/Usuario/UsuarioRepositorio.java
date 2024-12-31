package com.proyectofinal.forohubbackend.Domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
   UserDetails findByLogin(String login);
   @Query("SELECT u FROM Usuario u WHERE u.login = :username")
   Usuario findByUserName(String username);
}
