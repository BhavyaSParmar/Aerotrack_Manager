/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
public class Validation {
    
    public Boolean dateValidate(String date){
        
        String parts[] = date.split("/");
        int count = 0;
        Boolean flag = true;
        
        for(String part: parts) {
            int p = Integer.parseInt(part);
            
            if( count == 0 && (p > 12 || p < 0)){
                flag = false;
            }
            if( count == 1 && (p > 30 || p < 0)){
                flag = false;
            }
            if( count == 2 && (p > 3000 || p < 1999)){
                flag = false;
            }
            count++;
        }
    
        return flag;
    }
    
    
    public Boolean onlyCharacters(String characters){
        
        return characters.matches("[a-zA-Z]+");
    }
    
     public Boolean onlyCharactersWithSpace(String characters){
        
        return characters.matches("[a-zA-Z\\s]+");
    }
    
    public Boolean salaryValidate(String salary){
        
        return salary.matches("[0-9,]+");
    }
    
    public Boolean onlyNumbers(String number){
        
        if(number.length() == 0){
            return false;
        }
        try{
            Long.parseLong(number);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
    
    public Boolean emailValidation(String email){
    
        String regex = "^(.+)@(.+)$";  
        //Compile regular expression to get the pattern  
        Pattern pattern = Pattern.compile(regex);  
        
        Matcher matcher = pattern.matcher(email);  
        System.out.println(matcher.matches());
        
        return matcher.matches();
    
    }
    
    
    
    public Boolean specialCharacters(String number){
        
        for (int i = 0; i < number.length(); i++) {
 
            // Checking the character for not being a
            // letter,digit or space
            if (!Character.isDigit(number.charAt(i))
                && !Character.isLetter(number.charAt(i))) {
                return false;
            }
        }
        
        
        return true;
    }
    
}
