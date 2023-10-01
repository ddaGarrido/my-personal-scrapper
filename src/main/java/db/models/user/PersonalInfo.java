package db.models.user;

import db.models.DBModel;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PersonalInfos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonalInfo extends DBModel {
    @Id
    private ObjectId PIId;

    private ObjectId accountId;

    private String name;
    private String address;
    private String phone;
}
