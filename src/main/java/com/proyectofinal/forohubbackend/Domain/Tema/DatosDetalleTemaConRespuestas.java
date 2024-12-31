package com.proyectofinal.forohubbackend.Domain.Tema;

import com.proyectofinal.forohubbackend.Domain.Respuesta.Respuesta;

import java.util.List;

public record DatosDetalleTemaConRespuestas(
        long id,

        String titulo,


        String mensaje,

        String fecha,

        String lenguaje,

        String usuario,

        List<String> respuestas
) {


}
