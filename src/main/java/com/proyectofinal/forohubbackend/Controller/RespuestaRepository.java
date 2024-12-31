package com.proyectofinal.forohubbackend.Controller;

import com.proyectofinal.forohubbackend.Domain.Respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
