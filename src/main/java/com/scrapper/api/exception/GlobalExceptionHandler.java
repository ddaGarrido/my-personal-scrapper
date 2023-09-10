package com.scrapper.api.exception;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;

// import com.scrapper.api.dto.AuthenticateDTO;
// import com.scrapper.api.dto.SiteStatusDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

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
