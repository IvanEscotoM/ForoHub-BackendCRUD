package com.proyectofinal.forohubbackend.Controller;

import com.proyectofinal.forohubbackend.Domain.Tema.*;
import com.proyectofinal.forohubbackend.Domain.Usuario.Usuario;
import com.proyectofinal.forohubbackend.Domain.Usuario.UsuarioRepositorio;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tema")
public class TemaController {
    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping
    public ResponseEntity<DatosDetalleTema> postearTema(@RequestBody DatosPostTema datosPostTema, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        String username=(String)request.getAttribute("username");
        if (username == null) {
            return ResponseEntity.badRequest().body(null); // Manejo de error si no hay usuario
        }

        System.out.println(username);

        Usuario usuario=usuarioRepositorio.findByUserName(username);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Usuario no encontrado
        }
        Tema tema = temaRepository.save(new Tema(datosPostTema,usuario));
        DatosDetalleTema datosDetalleTema = new DatosDetalleTema(tema.getId(),tema.getTitulo(),tema.getMensaje(),tema.getFecha().toString(),tema.getLenguaje().toString(),tema.getUsuario().getUsername(),tema.getRespuestas().size());
        URI uri = uriBuilder.path("/tema/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(uri).body(datosDetalleTema);
    }

    //size = 2, direction = Sort.Direction.DESC,
    @GetMapping
    public ResponseEntity<Page<DatosListadoTema>> listarTemas(@PageableDefault(size = 10, page = 0, sort = "fecha", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(temaRepository.findByActivoTrue(pageable).map(DatosListadoTema::new));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTema(@RequestBody @Valid DatosActualizarTema datosActualizarTema, UriComponentsBuilder uriBuilder) {
        Tema tema = temaRepository.getReferenceById(datosActualizarTema.id());
        tema.actualizarTema(datosActualizarTema);
        return ResponseEntity.ok(new DatosDetalleTema(tema.getId(), tema.getTitulo(), tema.getMensaje(),tema.getFecha().toString(), tema.getLenguaje().toString(),tema.getUsuario().getUsername(),tema.getRespuestas().size()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTema(@PathVariable Long id) {
        Tema tema = temaRepository.getReferenceById(id);
        tema.desactivarTema();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity retornaTemaEspecifico(@PathVariable Long id) {
        Tema tema = temaRepository.getReferenceById(id);
        DatosDetalleTemaConRespuestas datosDetalleTema= new DatosDetalleTemaConRespuestas(tema.getId(), tema.getTitulo(), tema.getMensaje(),tema.getFecha().toString(), tema.getLenguaje().toString(),tema.getUsuario().getUsername(),tema.getRespuestas().stream().map(r->r.getUsuario().getUsername()+":    "+r.getMensaje()).toList());
        return ResponseEntity.ok(datosDetalleTema);
    }

}

