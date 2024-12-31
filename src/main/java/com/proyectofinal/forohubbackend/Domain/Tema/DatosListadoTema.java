package com.proyectofinal.forohubbackend.Domain.Tema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosListadoTema(
        Long id,
        String titulo,
        String fecha,
        int respuestas


) {
    public DatosListadoTema(Tema tema) {
        this(tema.getId(), tema.getTitulo(), tema.getFecha().toString(),tema.getRespuestas().size());

    }
}
