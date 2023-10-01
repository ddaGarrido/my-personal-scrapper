package db.repositories;

import org.bson.types.ObjectId;
import java.util.List;

public interface Repository<T> {
    T findById(ObjectId id);
    List<T> findAll();
    void save(T t);
}
