/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aayush
 */
public class Airplane {

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
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
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return the engine
     */
    public int getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(int engine) {
        this.engine = engine;
    }

    /**
     * @return the aisle
     */
    public String getAisle() {
        return aisle;
    }

    /**
     * @param aisle the aisle to set
     */
    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
    
    private String company;
    private String flight;
    private String model;
    private int seats;
    private int engine;
    private String aisle;
    
    
    
}
