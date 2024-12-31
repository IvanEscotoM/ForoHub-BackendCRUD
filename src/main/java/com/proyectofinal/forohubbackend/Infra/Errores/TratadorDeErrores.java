package com.proyectofinal.forohubbackend.Infra.Errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores {
    private record DatosErrorValidacion(String campo,String error){
        public DatosErrorValidacion(FieldError error){
            //En esta caso para el tratador de errores, esta en default,
            //la aplicacion mostrara los defaultMessages en ingles
            this(error.getField(),error.getDefaultMessage());
        }
    }

   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> fieldError = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
                return ResponseEntity.badRequest().body(fieldError);
    }





}
