package db.repositories;

import db.models.ConnectorModel;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectorRepository extends GenRepository<ConnectorModel> {

    public ConnectorRepository() {
        super("Connectors", ConnectorModel.class);
    }
}
