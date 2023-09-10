package com.scrapper.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteStatus {
    //basics
    private String siteName;
    private String title;

    //additional information
    private String description;
    private String logoURL;

    //validations
    private boolean hasLoginForm;
    private boolean hasUsernameField;
    private boolean hasPasswordField;
}
