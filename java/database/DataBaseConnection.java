/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author aayush
 */
public class DataBaseConnection {
    
    
    private static DataBaseConnection instance = null;
    public MongoDatabase database;
    
    private DataBaseConnection()
    {
        ConnectionString connectionString = new ConnectionString("");
        
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                                             pojoCodecRegistry);
        
        MongoClientSettings clientSettings;
        clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        
        MongoClient client = (MongoClient) MongoClients.create(clientSettings);
        database = client.getDatabase("AirplaneManagement");
       
    }
    
      public static DataBaseConnection connectToDatabase()
    {
        if (instance == null)
            instance = new DataBaseConnection();
  
        return instance;
    }
    
}
