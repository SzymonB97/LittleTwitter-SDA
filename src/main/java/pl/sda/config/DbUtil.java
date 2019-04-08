package pl.sda.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.util.Objects;

public class DbUtil {

    public static final String HOST = "localhost";
    public static final int PORT = 27017;
    public static final String DB_NAME = "littleTwitter";
    private static MongoClient instance = null;

    public static MongoDatabase getConnection() {
        if (Objects.isNull(instance)) {
            instance = new MongoClient(HOST, PORT);
        }

        return instance.getDatabase(DB_NAME);
    }

    public static void closeConnection() {
        instance.close();
        instance = null;
    }
}
