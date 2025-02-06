/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.airplanemanagement;
//import FleetManagement.FleetManagement;
import view.fleetmanagement.FleetManagement;
import ui.MainJFrame;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.Connection;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;


/**
 *
 * @author aayush
 */
public class AirplaneManagement {

    public static void main(String[] args) {
        // connecting to database
        MongoDatabase database = new Connection().connectToDatabase();
        
                        
        MainJFrame MainAdmin = new MainJFrame(database);
        MainAdmin.show();   
    }
}
