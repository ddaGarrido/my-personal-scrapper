package db.repositories.user;

import db.models.user.User;
import db.repositories.GenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenRepository<User> {

    public UserRepository() {
        super("Users", User.class);
    }
}