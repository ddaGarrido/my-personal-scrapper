package db.repositories.user;

import db.models.user.PersonalInfo;
import db.repositories.AbstractRepository;

public class PersonalInfoRepository extends AbstractRepository<PersonalInfo> {

    public PersonalInfoRepository() {
        super("PersonalInfos", PersonalInfo.class);
    }
}
