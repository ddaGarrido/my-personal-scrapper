package com.scrapper.api.dto;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@Builder
public class ConnectorsDTO {
    private ObjectId id;
    private String name;

    public ConnectorsDTO(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }
    // Construtores, getters e setters...
}
