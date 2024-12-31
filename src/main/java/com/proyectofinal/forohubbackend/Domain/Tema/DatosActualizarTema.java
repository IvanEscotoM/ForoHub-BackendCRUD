package com.proyectofinal.forohubbackend.Domain.Tema;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTema(
        @NotNull Long id,
        String titulo,
        String mensaje

) {
}
