package com.example.tomco.appdevassignment;

import android.graphics.Bitmap;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppointmentStore {

    private
    String name = "";
    String location = "";
    String details = "";
    String day = "";
    String month = "";
    String year = "";
    String start = "";
    String end = "";
    String occurrence = "";

    public void setName(String _name){name = _name;}

    public String getName(){
        return name;
    }

    public void setLocation(String _loc){location = _loc;}

    public String getLocation(){
        return location;
    }

    public void setDetails(String _det){details = _det;}

    public String getDetails(){
        return details;
    }

    public void setDay(String _day){day = _day;}

    public String getDay(){
        return day;
    }

    public void setMonth(String _month){month = _month;}

    public String getMonth(){
        return month;
    }

    public void setYear(String _year){year = _year;}

    public String getYear(){
        return year;
    }

    public void setStart(String _start){start = _start;}

    public String getStart(){
        return start;
    }

    public void setEnd(String _end){end = _end;}

    public String getEnd(){
        return end;
    }

    public void setOccurrence(String _ocur){occurrence = _ocur;}

    public String getOccurrence(){
        return occurrence;
    }
}
