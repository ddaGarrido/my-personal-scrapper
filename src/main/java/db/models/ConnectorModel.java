package db.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Connectors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConnectorModel extends DBModel{
    @Id
    private ObjectId connectorId;
    private String name;
    private String type;
    private String status;

    public ConnectorModel(String name, String type, String status) {
        this.name = name;
        this.type = type;
        this.status = status;
    }
}
