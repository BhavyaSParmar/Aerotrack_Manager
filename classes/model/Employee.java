/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class Employee {

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Designation
     */
    public String getDesignation() {
        return Designation;
    }

    /**
     * @param Designation the Designation to set
     */
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return the manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * @return the dateOfJoining
     */
    public String getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * @param dateOfJoining the dateOfJoining to set
     */
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * @return the number
     */
    public long getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the flights
     */
public ArrayList<ObjectId> getFlights() {
        return flights;
    }
public void setFlights(ArrayList<ObjectId> flights) {
        this.flights = flights;
    }public void addFlight(ObjectId flightId) {
        this.flights.add(flightId);
    }
    private String name;
    private String Designation;
    private String salary;
    private String manager;
    private String dateOfJoining;
    private long number;
    private String email;
  //  private ObjectId[] flights = null;
     private ArrayList<ObjectId> flights = new ArrayList<>();

    
    
    
    
}
