package db.repositories;

import db.models.Connector;

public class ConnectorRepository extends AbstractRepository<Connector> {

    public ConnectorRepository() {
        super("Connectors", Connector.class);
    }
}
