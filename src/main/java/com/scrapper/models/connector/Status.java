package com.scrapper.models.connector;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    //basics
    private String name;
    private String title;
    private int statusCode;
    private long responseTime; // em milissegundos
    // private LocalDateTime timestamp;
}
