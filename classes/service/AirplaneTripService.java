/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aayush
 */
package service;

import com.google.gson.Gson;
import com.mongodb.client.MongoDatabase;
import database.CRUDOperations;
import database.DataBaseConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.AfterFlight;
import model.AirplaneTrip;
import model.Checks;
import org.bson.Document;
import org.bson.types.ObjectId;

public class AirplaneTripService {

    private CRUDOperations crud = new CRUDOperations();



    public List<AirplaneTrip> fetchPlaneTripsByID(ObjectId id) {
        List<AirplaneTrip> tripList = new ArrayList<AirplaneTrip>();
        MongoDatabase database = DataBaseConnection.connectToDatabase().database;
        Document tripsList = this.crud.getFirstRecordByKey("planeId", id, this.crud.getCollection("trips", database));
        System.out.println("trip.get(\"checks\")"+tripsList);
        if (tripsList != null) {
            Document tripsData = (Document) tripsList.get("trips");

            tripsData.forEach((key, value) -> {

                Document trip = (Document) value;
                List<String> data = trip.getList("pilots", String.class);
                String[] pilots = new String[data.size()];
                data.toArray(pilots);
                data = trip.getList("hostess", String.class);
                String hostess[] = new String[data.size()];
                data.toArray(hostess);
                Checks check = null;//trip.get("checks", Checks.class);
                
                if(trip.get("checks") !=null){
                    Document d= (Document) trip.get("checks");
                    check= new Checks(d.getString(""),
                            d.getBoolean("pilotsTubes"), d.getBoolean("staticPorts"), d.getBoolean("landingGears"), d.getBoolean("steeringPins"), d.getInteger("brakeWear"), d.getInteger("tirePressure"), d.getString("objectsWings"), d.getString("radioChecks"), d.getBoolean("preFlightCleanup"), d.getBoolean("cabinCheck"), d.getBoolean("cabinPressure"), d.getString("checkMethod"));
                }
                
                AfterFlight afterFlight = null;
                if(trip.get("afterFlight")!=null){
                    Document d= (Document) trip.get("afterFlight");
                    afterFlight=new AfterFlight(d.getInteger("tireWear"), d.getInteger("landingGear"), d.getInteger("birdHits"), d.getString("engineCondition"), d.getString("fuelConsumed"), d.getString("objectCleaned"), d.getString("objectWings"), d.getInteger("tirePressure"));
                }
                    AirplaneTrip trips = new AirplaneTrip(trip.getString("from"),
                        trip.getString("to"),
                        trip.getString("flight"),
                        trip.getString("layover"),
                        trip.getString("traveTime"),
                        trip.getDate("departureDate"),
                        trip.getDate("arrivalDate"),
                        trip.getString("departureTime"),
                        trip.getString("ArrivalTime"),
                        trip.getInteger("averageSpeed"),
                        trip.getInteger("averageHeight"),
                        trip.getInteger("economy"),
                        trip.getInteger("premiumEconomy"),
                        trip.getInteger("business"),
                        trip.getInteger("mileage"),
                        trip.getInteger("stops"),
                        trip.getString("layoverTime"), check, afterFlight, pilots, hostess);
               trips.setDbDocument(trip);
                tripList.add(trips);
            });

        }
        return tripList;
    }

    public boolean deleteTripAndPlane(Object id) {
        MongoDatabase database = DataBaseConnection.connectToDatabase().database;
        try {
            this.crud.deleteDocumentById("_id", id, this.crud.getCollection("airplanes", database));
        this.crud.deleteDocumentById("planeId", id, this.crud.getCollection("trips", database));
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
