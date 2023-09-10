package com.scrapper.util;

import com.scrapper.connectors.Connector;
import com.scrapper.connectors.example.BaseConnector;

public class ConnectorFactory {

    public static Connector getConnector(String siteName) {
        switch (siteName.toLowerCase()) {
            case "example":
                return new BaseConnector();
            // Adicione cases para outros conectores conforme você os cria
            default:
                return null;
        }
    }
}
