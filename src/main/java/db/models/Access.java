package db.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Accesses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Access extends DBModel{
    @Id
    private ObjectId accessId;

    private ObjectId accountId;

    private String status;
}
