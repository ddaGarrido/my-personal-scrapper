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
public class Connector extends DBModel{
    @Id
    private ObjectId connectorId;
    private String name;
    private String type;
    private String status;
}
