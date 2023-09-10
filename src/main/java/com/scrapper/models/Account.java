package com.scrapper.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String id;
    private String name;
    private String status;
    private String formaCobranca;
    private String diaVencimento;
    private String observacao;
}

