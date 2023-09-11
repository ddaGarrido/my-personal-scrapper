package com.scrapper.connectors;

import org.springframework.stereotype.Component;

import com.scrapper.api.dto.AuthenticateDTO;
import com.scrapper.api.dto.SiteStatusDTO;
import com.scrapper.util.http.Http;

@Component
public interface Connector {
    public static int ID = 0;
    public static String NAME = "";
    public static String BASE_URL = "";

    public abstract int getId();
    public abstract String getName();

    public Http http = new Http();

    // Verifica a disponibilidade do site
    public abstract SiteStatusDTO checkSiteStatus();

    // Autentica no site usando credenciais fornecidas
    public abstract AuthenticateDTO authenticate(String username, String password);

    // Desloga do site
    public abstract AuthenticateDTO logoff();

    // ... qualquer outro método comum que você queira adicionar ...
}
