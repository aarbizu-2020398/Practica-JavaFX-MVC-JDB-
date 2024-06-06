/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.adrianarbizu.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    
    static private PasswordUtils instance;
    
    private PasswordUtils(){
        
    }
    
    public static PasswordUtils getInstance(){
        if(instance == null){
            instance = new PasswordUtils();
        }
        return instance;
    }
    
    public String encryptedPassword(String passWord){
        return BCrypt.hashpw(passWord, BCrypt.gensalt());
    }
    
    public boolean checkPassword(String password, String passwordEncrypted){
        return BCrypt.checkpw(password, passwordEncrypted);
    }
    
    
}
