package com.proyectofinal.forohubbackend.Domain.Tema;

import com.proyectofinal.forohubbackend.Domain.Respuesta.Respuesta;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import com.proyectofinal.forohubbackend.Domain.Usuario.UsuarioRepositorio;
import com.proyectofinal.forohubbackend.Infra.Security.TokenService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Table(name = "temas")
@Entity(name="Tema")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private Lenguaje lenguaje;
    private boolean activo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas;



    public Tema(DatosPostTema tema,Usuario usuario){
        this.titulo= tema.titulo();
        this.mensaje= tema.mensaje();
        this.fecha= LocalDateTime.now();
        this.lenguaje=tema.lenguaje();
        this.activo= true;
        this.usuario= usuario;
        this.respuestas= new ArrayList<>();
    }

    public void actualizarTema(DatosActualizarTema tema){
        if(tema.titulo() != null){
            this.titulo= tema.titulo();
        }
        if(tema.mensaje() != null){
            this.mensaje= tema.mensaje();
        }
        this.fecha= LocalDateTime.now();
    }

    public void desactivarTema(){
        this.activo= false;
    }

}
