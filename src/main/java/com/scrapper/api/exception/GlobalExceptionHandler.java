package com.scrapper.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Um erro ocorreu: " + ex.getMessage());
    }

    // @ExceptionHandler(ConnectorNotFoundException.class)
    // public ResponseEntity<SiteStatusDTO> handleConnectorNotFoundException(ConnectorNotFoundException ex) {
    //     SiteStatusDTO response = new SiteStatusDTO(false, ex.getMessage(), "CONNECTOR_NOT_FOUND");
    //     return ResponseEntity.badRequest().body(response);
    // }

    // // Para capturar todas as outras exceções não especificadas
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<AuthenticateDTO> handleGenericException(Exception ex) {
    //     AuthenticateDTO response = new AuthenticateDTO(false, "Erro interno do servidor", "INTERNAL_SERVER_ERROR");
    //     return ResponseEntity.status(500).body(response);
    // }

    // public static class ConnectorNotFoundException extends RuntimeException {
    //     public ConnectorNotFoundException(String message) {
    //         super(message);
    //     }
    // }
}
