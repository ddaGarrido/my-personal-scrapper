package db.repositories.user;

import db.models.user.User;
import db.repositories.AbstractRepository;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        super("Users", User.class);
    }
}