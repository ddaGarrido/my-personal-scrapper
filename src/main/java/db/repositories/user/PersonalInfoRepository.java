package db.repositories.user;

import db.models.user.PersonalInfo;
import db.repositories.GenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonalInfoRepository extends GenRepository<PersonalInfo> {

    public PersonalInfoRepository() {
        super("PersonalInfos", PersonalInfo.class);
    }
}
