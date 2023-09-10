package com.scrapper.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateDTO {
    public boolean success; // Indica se o login foi bem-sucedido ou não
    public String message;  // Mensagem detalhada sobre o status do login (por exemplo, "Login bem-sucedido", "Credenciais inválidas", etc.)
    public int statusCode; // Um código de erro opcional para erros específicos (por exemplo, "AUTH_FAILED", "API_ERROR", etc.)

}
