/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Lenovo
 */
public class HotelInformation implements Comparable<HotelInformation>, Serializable{
    String id;
    String name;
    int room;
    String address;
    String phone;
    int rating;
    
    @Override
    public String toString() {
      
        System.out.println( String.format("|  %3s   |  %-10s  |       %-8d     |  %-68s| %-11s |    %1d star    |", id.toUpperCase(),name,room,address,phone,rating));
    return String.format("_________________________________________________________________________________________________________________________________________________");
       
    }

    
    public HotelInformation(String id, String name, int room, String address, String phone, int rating) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }



//    @Override
//    public Comparator<HotelInformation> reversed() {
//        return Comparator.super.reversed(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }

    //@Override
    public int compareTo(HotelInformation otherObject) {
        return this.getName().compareToIgnoreCase(otherObject.getName());
    }

    
    
    
}
