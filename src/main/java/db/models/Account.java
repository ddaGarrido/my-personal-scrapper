package db.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account extends DBModel{
    @Id
    private ObjectId accountId;

    private ObjectId userId;
    private ObjectId connectorId;

    private String username;
    private String password;

}
