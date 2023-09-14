package com.scrapper.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operation {

    private LocalDateTime timestamp;
    private List<RequestLog> requests;

    public Operation() {
        this.timestamp = null;
        this.requests = new ArrayList<>();
    }
}