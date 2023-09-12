package com.scrapper.util;

import com.scrapper.connectors.Connector;
import com.scrapper.connectors.example.BaseConnector;
import com.scrapper.connectors.web.enel.Enel;
import com.scrapper.connectors.web.lesteTelecom.LesteTelecom;

public class ConnectorFactory {
    public static Connector getConnector(int connectorId) {
        switch (connectorId) {
            case 1:
                return new BaseConnector();
            case 2:
                return new LesteTelecom();
            case 3:
                return new Enel();
            default:
                return null;
        }
    }
}
