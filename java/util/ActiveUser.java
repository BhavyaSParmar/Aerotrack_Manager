/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.Users;

/**
 *
 * @author Asus
 */
public class ActiveUser {
    
    private static Users user;
    
    private ActiveUser(){
    }
    
    public synchronized  static Users getActiveUser(){
        return ActiveUser.user;
    }
    
    public synchronized static void setActiveUser(Users user){
        ActiveUser.user=user;
    }
    
}
