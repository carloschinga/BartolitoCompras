package com.bartolito.compras.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Error de base de datos
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseError(DataAccessException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"Error de acceso a SQL Server\" }";
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, json);
    }

    // Ruta no encontrada (404)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFound(NoHandlerFoundException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"Recurso no encontrado\" }";
        return buildResponse(HttpStatus.NOT_FOUND, json);
    }

    /*==================Método HTTP no permitido (405)================*/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"Método HTTP no permitido\" }";
        return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, json);
    }

    // Errores de validación (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationError(MethodArgumentNotValidException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"Parámetros inválidos\" }";
        return buildResponse(HttpStatus.BAD_REQUEST, json);
    }

    // Cualquier otro error inesperado (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericError(Exception ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"Error inesperado en la API\" }";
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, json);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidBody(HttpMessageNotReadableException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"El cuerpo de la petición es inválido o está vacío\" }";
        return buildResponse(HttpStatus.BAD_REQUEST, json);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<String> handleTypeError(ClassCastException ex) {
        String json = "{ \"resultado\": \"error\", \"mensaje\": \"El tipo de dato enviado no es válido\" }";
        return buildResponse(HttpStatus.BAD_REQUEST, json);
    }

    // Utilidad para armar la respuesta siempre igual
    private ResponseEntity<String> buildResponse(HttpStatus status, String body) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body);
    }
}
