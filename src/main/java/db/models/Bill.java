package db.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bill extends DBModel{
    @Id
    private ObjectId billId;

    private ObjectId accountId;
    private ObjectId accessId;

    private String billType;
    private String billDate;
    private String billAmount;
    private String billStatus;
}
