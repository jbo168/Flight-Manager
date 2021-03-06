package com.flightmanager.app.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Darius Gaurilka
 **/

public class AuthenticationAndLogoutContext {

    private String username;
    private boolean status;

    public AuthenticationAndLogoutContext(String username, boolean status){
        this.username = username;
        this.status = status;
    }

    public String getDate(){
        DateFormat chosenFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return chosenFormat.format(date);
    }

    public long getTime(){
        Date date = new Date();
        return date.getTime();
    }

    public String getUser(){
        return username;
    }

    public boolean getStatus(){
        return status;
    }
}
