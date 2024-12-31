package com.proyectofinal.forohubbackend.Domain.Tema;

import java.time.LocalDateTime;

public record DatosDetalleTema(
        long id,
        String titulo,
        String mensaje,
        String fecha,
        String lenguaje,
        String usuario,
        int respuestas
) {


}
