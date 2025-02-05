/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author aayush
 */
public class Checks {
    
    private String fuel;
    private Boolean pilotsTubes;
    private Boolean staticPorts;
    private Boolean landingGears;
    private Boolean steeringPins;
    private int brakeWear;
    private int tirePressure;
    private String objectsWings;
    private String radioChecks;
    private Boolean preFlightCleanup;
    private Boolean cabinCheck;
    private Boolean cabinPressure;
    private String checkMethod;
    
    public Checks(){
        
        this.fuel = "Full";
        this.pilotsTubes = true;
        this.staticPorts = true;
        this.landingGears = true;
        this.steeringPins = true;
        this.brakeWear = 10;
        this.tirePressure = 200;
        this.objectsWings = "None";
        this.radioChecks = "correct";
        this.preFlightCleanup = true;
        this.cabinCheck = true;
        this.cabinPressure = true;
        this.checkMethod = "Arrow";
    
    }

    public Checks(String fuel, Boolean pilotsTubes, Boolean staticPorts, Boolean landingGears, Boolean steeringPins, int brakeWear, int tirePressure, String objectsWings, String radioChecks, Boolean preFlightCleanup, Boolean cabinCheck, Boolean cabinPressure, String checkMethod) {
        this.fuel = fuel;
        this.pilotsTubes = pilotsTubes;
        this.staticPorts = staticPorts;
        this.landingGears = landingGears;
        this.steeringPins = steeringPins;
        this.brakeWear = brakeWear;
        this.tirePressure = tirePressure;
        this.objectsWings = objectsWings;
        this.radioChecks = radioChecks;
        this.preFlightCleanup = preFlightCleanup;
        this.cabinCheck = cabinCheck;
        this.cabinPressure = cabinPressure;
        this.checkMethod = checkMethod;
    }
    
    

    /**
     * @return the fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * @return the pilotsTubes
     */
    public Boolean getPilotsTubes() {
        return pilotsTubes;
    }

    /**
     * @param pilotsTubes the pilotsTubes to set
     */
    public void setPilotsTubes(Boolean pilotsTubes) {
        this.pilotsTubes = pilotsTubes;
    }

    /**
     * @return the staticPorts
     */
    public Boolean getStaticPorts() {
        return staticPorts;
    }

    /**
     * @param staticPorts the staticPorts to set
     */
    public void setStaticPorts(Boolean staticPorts) {
        this.staticPorts = staticPorts;
    }

    /**
     * @return the landingGears
     */
    public Boolean getLandingGears() {
        return landingGears;
    }

    /**
     * @param landingGears the landingGears to set
     */
    public void setLandingGears(Boolean landingGears) {
        this.landingGears = landingGears;
    }

    /**
     * @return the steeringPins
     */
    public Boolean getSteeringPins() {
        return steeringPins;
    }

    /**
     * @param steeringPins the steeringPins to set
     */
    public void setSteeringPins(Boolean steeringPins) {
        this.steeringPins = steeringPins;
    }

    /**
     * @return the brakeWear
     */
    public int getBrakeWear() {
        return brakeWear;
    }

    /**
     * @param brakeWear the brakeWear to set
     */
    public void setBrakeWear(int brakeWear) {
        this.brakeWear = brakeWear;
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

    /**
     * @return the objectsWings
     */
    public String getObjectsWings() {
        return objectsWings;
    }

    /**
     * @param objectsWings the objectsWings to set
     */
    public void setObjectsWings(String objectsWings) {
        this.objectsWings = objectsWings;
    }

    /**
     * @return the radioChecks
     */
    public String getRadioChecks() {
        return radioChecks;
    }

    /**
     * @param radioChecks the radioChecks to set
     */
    public void setRadioChecks(String radioChecks) {
        this.radioChecks = radioChecks;
    }

    /**
     * @return the preFlightCleanup
     */
    public Boolean getPreFlightCleanup() {
        return preFlightCleanup;
    }

    /**
     * @param preFlightCleanup the preFlightCleanup to set
     */
    public void setPreFlightCleanup(Boolean preFlightCleanup) {
        this.preFlightCleanup = preFlightCleanup;
    }

    /**
     * @return the cabinCheck
     */
    public Boolean getCabinCheck() {
        return cabinCheck;
    }

    /**
     * @param cabinCheck the cabinCheck to set
     */
    public void setCabinCheck(Boolean cabinCheck) {
        this.cabinCheck = cabinCheck;
    }

    /**
     * @return the cabinPressure
     */
    public Boolean getCabinPressure() {
        return cabinPressure;
    }

    /**
     * @param cabinPressure the cabinPressure to set
     */
    public void setCabinPressure(Boolean cabinPressure) {
        this.cabinPressure = cabinPressure;
    }

    /**
     * @return the checkMethod
     */
    public String getCheckMethod() {
        return checkMethod;
    }

    /**
     * @param checkMethod the checkMethod to set
     */
    public void setCheckMethod(String checkMethod) {
        this.checkMethod = checkMethod;
    }
    
}
