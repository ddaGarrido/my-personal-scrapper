package db.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import db.MongoDBConfig;
import jakarta.annotation.PostConstruct;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class GenRepository<T> {

    protected final MongoCollection<T> collection;

    public GenRepository(String collectionName, Class<T> clazz) {
        MongoDatabase database = MongoDBConfig.getDatabase();
        collection = database.getCollection(collectionName, clazz);
    }

    @PostConstruct
    public T findById(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    @PostConstruct
    public List<T> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    @PostConstruct
    public void save(T t) {
        collection.insertOne(t);
    }
}
