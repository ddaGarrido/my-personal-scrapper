package com.scrapper.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String id;
    private String nome;
    private String email;
    private String celular;
    private String endereco;
}

