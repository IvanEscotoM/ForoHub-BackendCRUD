package com.proyectofinal.forohubbackend.Domain.Respuesta;

import com.proyectofinal.forohubbackend.Domain.Tema.Tema;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tema_id")
    private Tema tema;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Respuesta(DatosPostRespuesta datosPostRespuesta, Usuario usuario,Tema tema) {
        this.mensaje = datosPostRespuesta.mensaje();
        this.usuario = usuario;
        this.fecha = LocalDateTime.now();
        this.tema=tema;
    }


}
