/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import org.bson.Document;

/**
 *
 * @author aayush
 */
public class AirplaneTrip {
    
    private String from;
    private String to;
    private String flight;
    private String layover;
    private String traveTime;
    private Date departureDate;
    private Date arrivalDate;
    private String departureTime;
    private String ArrivalTime;
    private Integer averageSpeed;
    private Integer averageHeight;
    private Integer economy;
    private Integer premiumEconomy;
    private Integer business;
    private Integer mileage;
    private Integer stops;
    private String layoverTime;
    private Checks checks;
    private AfterFlight afterFlight;
    private String[] pilots;
    private String[] hostess;
    private Document dbDocument;
    
    public AirplaneTrip(){}
    
    public AirplaneTrip(String from, String to, String flight, String layover, String traveTime, Date departureDate, Date arrivalDate, String departureTime, String ArrivalTime, Integer averageSpeed, Integer averageHeight, Integer economy, Integer premiumEconomy, Integer business, Integer mileage, Integer stops, String layoverTime, Checks checks, AfterFlight afterFlight, String[] pilots, String[] hostess) {
        this.from = from;
        this.to = to;
        this.flight = flight;
        this.layover = layover;
        this.traveTime = traveTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.ArrivalTime = ArrivalTime;
        this.averageSpeed = averageSpeed;
        this.averageHeight = averageHeight;
        this.economy = economy;
        this.premiumEconomy = premiumEconomy;
        this.business = business;
        this.mileage = mileage;
        this.stops = stops;
        this.layoverTime = layoverTime;
        this.checks = checks;
        this.afterFlight = afterFlight;
        this.pilots = pilots;
        this.hostess = hostess;
    }
    
    
    

    /**
     * @return the froms
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the flight
     */
    public String getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * @return the layover
     */
    public String getLayover() {
        return layover;
    }

    /**
     * @param layover the layover to set
     */
    public void setLayover(String layover) {
        this.layover = layover;
    }

    /**
     * @return the traveTime
     */
    public String getTraveTime() {
        return traveTime;
    }

    /**
     * @param traveTime the traveTime to set
     */
    public void setTraveTime(String traveTime) {
        this.traveTime = traveTime;
    }

    /**
     * @return the date
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @param date the date to set
     */
    public void setDepartureDate(Date date) {
        this.departureDate = date;
    }
    
    /**
     * @return the date
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param date the date to set
     */
    public void setArrivalDate(Date date) {
        this.arrivalDate = date;
    }

    /**
     * @return the departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the ArrivalTime
     */
    public String getArrivalTime() {
        return ArrivalTime;
    }

    /**
     * @param ArrivalTime the ArrivalTime to set
     */
    public void setArrivalTime(String ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }

    /**
     * @return the averageSpeed
     */
    public Integer getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * @param averageSpeed the averageSpeed to set
     */
    public void setAverageSpeed(Integer averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * @return the averageHeight
     */
    public Integer getAverageHeight() {
        return averageHeight;
    }

    /**
     * @param averageHeight the averageHeight to set
     */
    public void setAverageHeight(Integer averageHeight) {
        this.averageHeight = averageHeight;
    }

    /**
     * @return the economy
     */
    public Integer getEconomy() {
        return economy;
    }

    /**
     * @param economy the economy to set
     */
    public void setEconomy(Integer economy) {
        this.economy = economy;
    }

    /**
     * @return the premiumEconomy
     */
    public Integer getPremiumEconomy() {
        return premiumEconomy;
    }

    /**
     * @param premiumEconomy the premiumEconomy to set
     */
    public void setPremiumEconomy(Integer premiumEconomy) {
        this.premiumEconomy = premiumEconomy;
    }

    /**
     * @return the business
     */
    public Integer getBusiness() {
        return business;
    }

    /**
     * @param business the business to set
     */
    public void setBusiness(Integer business) {
        this.business = business;
    }

    /**
     * @return the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the stops
     */
    public Integer getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(Integer stops) {
        this.stops = stops;
    }

    /**
     * @return the layoverTime
     */
    public String getLayoverTime() {
        return layoverTime;
    }

    /**
     * @param layoverTime the layoverTime to set
     */
    public void setLayoverTime(String layoverTime) {
        this.layoverTime = layoverTime;
    }

    /**
     * @return the checks
     */
    public Checks getChecks() {
        return checks;
    }

    /**
     * @param checks the checks to set
     */
    public void setChecks(Checks checks) {
        this.checks = checks;
    }

    /**
     * @return the afterFlight
     */
    public AfterFlight getAfterFlight() {
        return afterFlight;
    }

    /**
     * @param afterFlight the afterFlight to set
     */
    public void setAfterFlight(AfterFlight afterFlight) {
        this.afterFlight = afterFlight;
    }
    
    
    
    
    /**
     * @return the pilots
     */
    public String[] getPilots() {
        return pilots;
    }

    /**
     * @param pilots the pilots to set
     */
    public void setPilots(String[] pilots) {
        this.pilots = pilots;
    }

    /**
     * @return the hostess
     */
    public String[] getHostess() {
        return hostess;
    }

    /**
     * @param hostess the hostess to set
     */
    public void setHostess(String[] hostess) {
        this.hostess = hostess;
    }

    public Document getDbDocument() {
        return dbDocument;
    }

    public void setDbDocument(Document dbDocument) {
        this.dbDocument = dbDocument;
    }
    
    
    
    
}
