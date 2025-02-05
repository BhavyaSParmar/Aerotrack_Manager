/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

/**
 *
 * @author Asus
 */
public class LoginValidation {
    
    public Boolean username(String username){
        
        if(username.length() == 0){
            return false;
        }
        
        char[] chars = username.toCharArray();
        for(char c : chars){
           if(
                   Character.isDigit(c)
                   || !Character.isLetter(c)
                   || Character.isWhitespace(c)
                ){
              return false;
           }
        }
        
    
        return true;
    }
    
    public Boolean password(String password){
        
        if(password.length() == 0){
            return false;
        }
        
        char[] chars = password.toCharArray();
        for(char c : chars){
           if(
                   !Character.isLetter(c)
                   || Character.isWhitespace(c)
                ){
              return false;
           }
        }
        
    
        return true;
    }
    
}
