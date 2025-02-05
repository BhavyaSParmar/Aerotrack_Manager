/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class Pdf {

    /**
     * @return the id
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

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
     * @return the allowances
     */
    public String getAllowances() {
        return allowances;
    }

    /**
     * @param allowances the allowances to set
     */
    public void setAllowances(String allowances) {
        this.allowances = allowances;
    }

    /**
     * @return the deductions
     */
    public String getDeductions() {
        return deductions;
    }

    /**
     * @param deductions the deductions to set
     */
    public void setDeductions(String deductions) {
        this.deductions = deductions;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }
    
    private ObjectId id;
    private String name;
    private String email;
    private String salary;
    private String allowances;
    private String deductions;
    private String total;
    
}
