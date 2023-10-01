package db.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import db.MongoDBConfig;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<T> implements Repository<T> {

    protected final MongoCollection<T> collection;

    public AbstractRepository(String collectionName, Class<T> clazz) {
        MongoDatabase database = MongoDBConfig.getDatabase();
        collection = database.getCollection(collectionName, clazz);
    }

    @Override
    public T findById(ObjectId id) {
        return collection.find(Filters.eq("_id", id)).first();
    }

    @Override
    public List<T> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    @Override
    public void save(T t) {
        collection.insertOne(t);
    }
}
