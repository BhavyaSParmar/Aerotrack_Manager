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
public class PaintJob {

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
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
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
    
    
    private LocalDateTime date;
    private ObjectId planeId;
    private String color;
    private String status;
    private Date dueDate;
    
    
    
    public PaintJob(LocalDateTime date, ObjectId planeId, String color, String status, Date dueDate){
        
        this.date = date;
        this.status = status;
        this.planeId = planeId;
        this.color = color;
        this.dueDate = dueDate;
    
    
    }
    
}
