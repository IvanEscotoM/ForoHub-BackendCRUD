package com.proyectofinal.forohubbackend.Controller;

import com.proyectofinal.forohubbackend.Domain.Respuesta.DatosDetalleRespuesta;
import com.proyectofinal.forohubbackend.Domain.Respuesta.DatosPostRespuesta;
import com.proyectofinal.forohubbackend.Domain.Respuesta.Respuesta;
import com.proyectofinal.forohubbackend.Domain.Tema.DatosDetalleTema;
import com.proyectofinal.forohubbackend.Domain.Tema.DatosPostTema;
import com.proyectofinal.forohubbackend.Domain.Tema.Tema;
import com.proyectofinal.forohubbackend.Domain.Tema.TemaRepository;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import com.proyectofinal.forohubbackend.Domain.Usuario.UsuarioRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tema/{id}/respuestas")
public class RespuestasController {

    @Autowired
    private RespuestaRepository respuestaRepository;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private TemaRepository temaRepository;

    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> postearTema(@PathVariable Long id, @RequestBody DatosPostRespuesta datosPostRespuesta, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        String username=(String)request.getAttribute("username");
        if (username == null) {
            return ResponseEntity.badRequest().body(null);
        }
        Usuario usuario=usuarioRepositorio.findByUserName(username);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Tema tema= temaRepository.getReferenceById(id);
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosPostRespuesta,usuario, tema));
        DatosDetalleRespuesta datosDetalleRespuesta = new DatosDetalleRespuesta(respuesta.getId(),respuesta.getMensaje(),respuesta.getUsuario().getUsername(),respuesta.getFecha().toString());



        URI uri = uriBuilder.path("/tema/{id}/respuestas/{respuestaId}").buildAndExpand(id,respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleRespuesta);
    }
    }
