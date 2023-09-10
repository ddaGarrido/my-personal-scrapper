package com.scrapper.api.dto;

import com.scrapper.models.SiteStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
// import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SiteStatusDTO extends SiteStatus{
    //basics
    private int statusCode;
    private long responseTime; // em milissegundos
    // private LocalDateTime timestamp;

    //error
    private String message;
}
