/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author aayush
 */
public class Repair {
    private ObjectId id;
    private Date date;
    private String status;
    private Date dueDate;
    private String repairPart;
    private ObjectId userId;
    private String description;
    private ObjectId planeId;
    public Repair(ObjectId id,ObjectId planeId, Date date, String status, Date dueDate, String repairPart, ObjectId userId, String description) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.dueDate = dueDate;
        this.repairPart = repairPart;
        this.userId = userId;
        this.description = description;
        this.planeId=planeId;
    }

    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getRepairPart() {
        return repairPart;
    }

    public void setRepairPart(String repairPart) {
        this.repairPart = repairPart;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getPlaneId() {
        return planeId;
    }

    public void setPlaneId(ObjectId planeId) {
        this.planeId = planeId;
    }
    
    
}