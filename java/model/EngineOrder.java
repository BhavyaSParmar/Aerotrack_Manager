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
public class EngineOrder {

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
    
    private ObjectId userId;
    private LocalDateTime date;
    private String status;
    private ObjectId planeId;
    
    
    public EngineOrder(ObjectId userId, LocalDateTime date, String status, ObjectId planeId){
        
        
        this.userId = userId;
        this.date = date;
        this.status = status;
        this.planeId = planeId;
    
    
    }
    
    
}
