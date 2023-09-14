package com.scrapper.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConnectorsDTO {
    private int id;
    private String name;

    public ConnectorsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }
    // Construtores, getters e setters...
}
