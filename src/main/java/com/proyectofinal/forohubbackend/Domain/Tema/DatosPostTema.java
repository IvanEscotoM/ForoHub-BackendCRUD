package com.proyectofinal.forohubbackend.Domain.Tema;

import jakarta.validation.constraints.NotBlank;

public record DatosPostTema(
        @NotBlank String titulo,
        String mensaje,
        @NotBlank Lenguaje lenguaje

) {
}
