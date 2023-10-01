package db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBConfig {
    private static final String CONNECTION_STRING = "mongodb+srv://danieldagarrido:tz89bVsh1U7gNr2g@cluster0.y6occph.mongodb.net/?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "PersonalScrapper";

    public static MongoClient connectMongoDB() {
        ConnectionString connectionString = new ConnectionString(CONNECTION_STRING);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        return MongoClients.create(clientSettings);
    }

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = connectMongoDB();
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
