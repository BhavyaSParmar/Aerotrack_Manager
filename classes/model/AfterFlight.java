/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aayush
 */
public class AfterFlight {
    
    private int tireWear;
    private int landingGear;
    private int birdHits;
    private String engineCondition;
    private String fuelConsumed;
    private String objectCleaned;
    private String objectWings;
    private int tirePressure;
    
    public AfterFlight(){
        
        this.tireWear = 20;
        this.landingGear = 5;
        this.birdHits = 0;
        this.engineCondition = "Good";
        this.fuelConsumed = "1/4";
        this.objectCleaned = "Snow Cleaned";
        this.objectWings = "Snow";
        this.tirePressure = 180;
    
    }

    public AfterFlight(int tireWear, int landingGear, int birdHits, String engineCondition, String fuelConsumed, String objectCleaned, String objectWings, int tirePressure) {
        this.tireWear = tireWear;
        this.landingGear = landingGear;
        this.birdHits = birdHits;
        this.engineCondition = engineCondition;
        this.fuelConsumed = fuelConsumed;
        this.objectCleaned = objectCleaned;
        this.objectWings = objectWings;
        this.tirePressure = tirePressure;
    }

    

    /**
     * @return the tireWear
     */
    public int getTireWear() {
        return tireWear;
    }

    /**
     * @param tireWear the tireWear to set
     */
    public void setTireWear(int tireWear) {
        this.tireWear = tireWear;
    }

    /**
     * @return the landingGear
     */
    public int getLandingGear() {
        return landingGear;
    }

    /**
     * @param landingGear the landingGear to set
     */
    public void setLandingGear(int landingGear) {
        this.landingGear = landingGear;
    }

    /**
     * @return the birdHits
     */
    public int getBirdHits() {
        return birdHits;
    }

    /**
     * @param birdHits the birdHits to set
     */
    public void setBirdHits(int birdHits) {
        this.birdHits = birdHits;
    }

    /**
     * @return the engineCondition
     */
    public String getEngineCondition() {
        return engineCondition;
    }

    /**
     * @param engineCondition the engineCondition to set
     */
    public void setEngineCondition(String engineCondition) {
        this.engineCondition = engineCondition;
    }

    /**
     * @return the fuelConsumed
     */
    public String getFuelConsumed() {
        return fuelConsumed;
    }

    /**
     * @param fuelConsumed the fuelConsumed to set
     */
    public void setFuelConsumed(String fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    /**
     * @return the objectCleaned
     */
    public String getObjectCleaned() {
        return objectCleaned;
    }

    /**
     * @param objectCleaned the objectCleaned to set
     */
    public void setObjectCleaned(String objectCleaned) {
        this.objectCleaned = objectCleaned;
    }

    /**
     * @return the objectWings
     */
    public String getObjectWings() {
        return objectWings;
    }

    /**
     * @param objectWings the objectWings to set
     */
    public void setObjectWings(String objectWings) {
        this.objectWings = objectWings;
    }

    /**
     * @return the tirePressure
     */
    public int getTirePressure() {
        return tirePressure;
    }

    /**
     * @param tirePressure the tirePressure to set
     */
    public void setTirePressure(int tirePressure) {
        this.tirePressure = tirePressure;
    }
      
}
