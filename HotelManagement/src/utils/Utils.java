/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import data.HotelInformation;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Utils {
    public static Scanner sc = new Scanner(System.in);
    
    public static int getIntegerChoice (String firstMsg, String errMsg, int min, int max) {
        int n;
        while (true) { //khi có return sẽ thoát vòng lặp
            try {
                System.out.print(firstMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max)
                    throw new Exception("Your choice are out of bound! Please enter in range (" +min+ "," +max+ ")");
                else return n;    
            } catch (Exception e) {
                System.out.println("Error cause - "+e.getMessage()+" \n"+errMsg);
            }
        }       
    }
    public static int updateRoomChoice (String firstMsg, String errMsg, int min, int max, HotelInformation hotel) {
        String input = "";
        int room;
        while (true) { //khi có return sẽ thoát vòng lặp
            try {
                System.out.print(firstMsg);
                input = sc.nextLine();
                if (input.trim().length() == 0 || input.isEmpty()) {
                    System.err.println("\"You left it blank. The old number of Rooms data field will not change.");
                    return hotel.getRoom();
                }
                room = Integer.parseInt(input);
                if (room < min || room > max)
                    throw new Exception("Your choice are out of bound! Please enter in range (" +min+ "," +max+ ")");
                else return room;    
            } catch (Exception e) {
                System.out.println("Error cause - "+e.getMessage()+" \n"+errMsg);
            }
        }       
    }
    public static int updateRatingChoice (String firstMsg, String errMsg, int min, int max, HotelInformation hotel) {
        String input = "";
        int rate;
        while (true) { //khi có return sẽ thoát vòng lặp
            try {
                System.out.print(firstMsg);
                input = sc.nextLine();
                if (input.trim().length() == 0 || input.isEmpty()) {
                    System.err.println("You left it blank. The old Rating data field will not change.");
                    return hotel.getRating();
                }
                rate = Integer.parseInt(input);
                if (rate < min || rate > max)
                    throw new Exception("Your choice are out of bound! Please enter in range (" +min+ "," +max+ ")");
                else return rate;    
            } catch (Exception e) {
                System.out.println("Error cause - "+e.getMessage()+" \n"+errMsg);
            }
        }       
    }
    
    
    
    
    //Cho ID và Phone
    public static String inputFormat (String firstMsg, String format, String errMsg) {
        String id;
        boolean check;
        
        while (true) {            
            System.out.print(firstMsg);
            id = sc.nextLine();
            if (id.trim().length() == 0 || id.isEmpty()) 
               System.out.println("Required fields must not be left blank!"); 
            else if (!id.matches(format)) 
                System.out.println(errMsg);
            else return id;
        }
    }
    public static String updatePhoneWithFormat (String firstMsg, String format, String errMsg, HotelInformation hotel) {
        String phone;
        boolean check;
        
        while (true) {            
            System.out.print(firstMsg);
            phone = sc.nextLine();
            if (phone.trim().length() == 0 || phone.isEmpty()) {
               System.err.println("You left it blank. The old Phone data field will not change."); 
               return hotel.getPhone();
            }
            else if (!phone.matches(format)) 
                System.out.println(errMsg);
            else return phone;
        }
    }
    
   
   
    
    //Dùng riêng cho name
    public static String inputNameString (String firstMsg, String errMsg) {
        String str = "";
        boolean flag = true;
        
        while (flag == true) {
            System.out.println(firstMsg);
            str = sc.nextLine();
            if (str.trim().length() == 0 || str.isEmpty())
                System.out.println(errMsg);
            else if (str.substring(0, 1).matches("[0-9]")) {
                System.out.println("Your hotel name seems to start with a digit...");
                flag = confirmYesNo("Would you like to change the name (Choose Y/N):");
            }
            else return str;
        }
        return str;
    }
    public static String updateNameString (String firstMsg, String errMsg, HotelInformation hotel) {
        String name = "";
        boolean flag = true;
        
        while (flag == true) {
            System.out.println(firstMsg);
            name = sc.nextLine();
            if (name.trim().length() == 0 || name.isEmpty()) {
                System.err.println("You left it blank. The old Hotel name field will not change.");
                return hotel.getName();
            }
            else if (name.substring(0, 1).matches("[0-9]")) {
                System.out.println("Your hotel name seems to start with a digit...");
                flag = confirmYesNo("Would you like to change the name (Choose Y/N):");
            }
            else return name;
        }
        return name;
    }
    
    //address
    public static String inputString (String firstMsg, String errMsg) {
        String str = "";
        while (true) {            
            System.out.print(firstMsg);
            str = sc.nextLine();
            if (str.length() == 0 || str.isEmpty()) {
                System.out.println(errMsg);
            } else return str;
        }
    }
    public static String updateAddressString (String firstMsg, String errMsg, HotelInformation hotel) {
        String address = "";
        while (true) {            
            System.out.print(firstMsg);
            address = sc.nextLine();
            if (address.length() == 0 || address.isEmpty()) {
                System.err.println("You left it blank. The old Address data field will not change.");
                return hotel.getAddress();
            } else return address;
        }
    }
    
    
    
    public static boolean confirmYesNo (String confirmMsg) {
        String confirm = "";
        
        while(true) {
            System.out.println(confirmMsg);
            confirm = sc.nextLine();
            if (confirm.matches("[Yy]es|[Yy]")) 
                return true;
            else if (confirm.matches("[Nn]o|[Nn]"))
                return false;
            else
                System.err.println("You must enter Y/N");
        }
        
    }
    
    
    // SAVE - LOAD 
    public static void saveToFile (ArrayList<HotelInformation> hList, String fileName) {
        HotelInformation tempHotel;
        
        try {
            File f = new File(fileName);
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream (fos);
            
            for (int i = 0; i < hList.size(); i++) {
                if (hList.get(i) != null) {
                    tempHotel = hList.get(i);
                    oos.writeObject(tempHotel);
                    
                }
            }
            //hList.clear(); 
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public static void loadFromFile (ArrayList<HotelInformation> hList, String fileName) {
        if (hList.size() > 0) {
            hList.clear();
        }
        
        try {
            File f = new File(fileName);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            
            while (true) {
                try {
                    HotelInformation o = (HotelInformation) ois.readObject();
                    hList.add(o); 
                } catch (EOFException e) {
                    break;
                }
            }
            
            fis.close();
            ois.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
