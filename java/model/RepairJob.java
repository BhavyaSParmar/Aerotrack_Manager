/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class RepairJob {

    /**
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * @return the planeId
     */
    public ObjectId getPlaneId() {
        return planeId;
    }

    /**
     * @param planeId the planeId to set
     */
    public void setPlaneId(ObjectId planeId) {
        this.planeId = planeId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the repairPart
     */
    public String getRepairPart() {
        return repairPart;
    }

    /**
     * @param repairPart the repairPart to set
     */
    public void setRepairPart(String repairPart) {
        this.repairPart = repairPart;
    }

    /**
     * @return the userId
     */
    public ObjectId getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    private LocalDateTime date;
    private ObjectId planeId;
    private String status;
    private Date dueDate;
    private String repairPart;
    private ObjectId userId;
    private String description;
    
    
    public RepairJob(LocalDateTime date,
            ObjectId planeId,
            String status,
            Date dueDate,
            String repairPart,
            ObjectId userId,
            String description
            ){
        
        this.date = date;
        this.planeId = planeId;
        this.status = status;
        this.dueDate = dueDate;
        this.repairPart = repairPart;
        this.userId = userId;
        this.description = description;
    
    
    }
    
    
    
    
}
