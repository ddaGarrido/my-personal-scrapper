package com.scrapper.util;

import com.scrapper.connectors.Connector;
import com.scrapper.connectors.web.enel.Enel;
import com.scrapper.connectors.web.lesteTelecom.LesteTelecom;

public class ConnectorFactory {
    public static Connector createConnector(int connectorId) {
        switch (connectorId) {
            case 1:
                return new LesteTelecom();
            case 2:
                return new Enel();
            default:
                throw new IllegalArgumentException("Invalid connector ID");
        }
    }
}
