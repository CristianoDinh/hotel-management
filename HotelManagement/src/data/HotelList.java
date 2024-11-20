package data;

import java.util.ArrayList;

import utils.Utils;

import java.util.Scanner;


/**
 *
 * @author Lenovo
 */
public class HotelList {

    ArrayList<HotelInformation> hList = new ArrayList<>();
    //HotelInformation hotel;
    Scanner sc = new Scanner(System.in);
    
    String fileName = "hotel.dat";
    //String fileName = "test.dat";
    
    public void loadDataToProgram() {
        if (!hList.isEmpty()) {
            System.err.println("Refuse to load data! You seems loading duplicated data!");
            return;
        }
        Utils.loadFromFile(hList, fileName);
        if (hList.isEmpty()) {
            System.err.println("Empty list, please add new one and then save data to File to have information in File 📂\n");
        } else {
            System.out.println("Loading data from file successfully ... Ready for your next operations\n");
        }
    }

    public void addNewHotel() {
        String id;
        String name;
        int room;
        String address;
        String phone;
        int rating;

        boolean choice = true;

        while (choice) {
            boolean checkID;
            do {
                checkID = true;
                id = Utils.inputFormat("Input hotel id in format (Hxx): ", "[H/h]\\d{2}", "Your ID must be start with 'H' & follow by 2 digit");
                for (HotelInformation hotel : hList) {
                    if (hotel.getId().equalsIgnoreCase(id)) {
                        System.err.println("ID Duplicated! You are adding existing ID...");
                        checkID = false;
                    }
                }
            } while (checkID == false);

            name = Utils.inputNameString("Input your hotel name: ", "Hotel name is required. Required fields must not be left blank!");
            room = Utils.getIntegerChoice("Total of unused rooms: ", "Each hotel is allowed to contain a maximum of 100 rooms & at least 1 room", 1, 100);
            address = Utils.inputString("Input your hotel address: ", "Hotel Address is required. Required fields must not be left blank!");
            phone = Utils.inputFormat("Your hotel hotline (Starting with '0' and followed by 9 number digits): ", "0\\d{9}", "Hotel phone number is required. Phone number must contain 10 number digits & start with ditgit 0");
            rating = Utils.getIntegerChoice("Your hotel quality by number of rating stars: ", "Quality rate must be evaluated from (1...6)", 1, 6);
            
            hList.add(new HotelInformation(id, name, room, address, phone, rating));
            System.out.println("A new Hotel is added sucessful");
            choice = Utils.confirmYesNo("Do you want to continue adding new hotel? (Y/N):  ");
        }

    }

    public void verifyHotel() {
        if (hList.isEmpty()) {
            System.err.println("Data in list is empty. Back to Menu & Select function number 2 to add a new one\n");
            return;
        }
        boolean confirm;
        boolean check = false;
        do {    
            String idCheck = Utils.inputString("ID of hotel that you want to find ? ", "This field must not be blank");

            for (int i = 0; i < hList.size(); i++) {
                if (idCheck.equalsIgnoreCase(hList.get(i).getId())) {
                    check = true;
                    System.out.println("Exist Hotel");
                    System.out.println("_________________________________________________________________________________________________________________________________________________");
                    System.out.printf("|%-8s|  %-8s  |%20s|                    %-50s| %-11s | %-13s|\n", "Hotel_id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
                    System.out.println("_________________________________________________________________________________________________________________________________________________");
                    System.out.println(hList.get(i).toString());
                    break;
                }
            }
            
            if(check == false) System.err.println("No Hotel Found!");
            
            
            confirm = Utils.confirmYesNo("Do you want to continue Searching hotel information? (Y/N): ");
        }while (confirm == true);
    }

    public void updateExsitingHotel() {
        if (hList.isEmpty()) {
            System.err.println("There is not any Hotel information for your updating!");
            System.out.println("\n******************************* Additional Options **********************************************");
            System.out.println("You could choose option 1 to Load Data from file so as to information being appeared for checking");
            System.out.println("You could choose option 2 to Add new information for a new Hotel");
            System.out.println("You could choose option 3 to Return back to the main menu");
            System.out.println("*************************************************************************************************");
            int subChoice = Utils.getIntegerChoice("Choose your option: ", "Please correct choice's format in range (1 to 3)", 1, 3);
            switch (subChoice) {
                case 1:
                    HotelList.this.loadDataToProgram();
                    return;
                case 2:
                    HotelList.this.addNewHotel();
                    return;
                case 3:
                    System.out.println("\n...Returning to main menu...\n");
                    return;
            }
        }
             
        String tmpName;
        int tmpRoom;
        String tmpAddress;
        String tmpPhone;
        int tmpRate;
        boolean confirm = true;
        
        boolean check=false;
        do {
            
            String existID = Utils.inputString("ID of hotel that you want to update ?", "This field must not be blank");
             
            for (int i = 0; i < hList.size(); i++) {
                HotelInformation hotel = hList.get(i);
                if (existID.equalsIgnoreCase(hList.get(i).getId())) {
                    check=true;
                    System.out.println("------- Please fill in with new information and Skip updating by Pressing Enter on your keyboard ------");
                    tmpName = Utils.updateNameString("Input new hotel name: ", "Hotel name is required. Required fields must not be left blank!",hotel);
                    tmpRoom = Utils.updateRoomChoice("New Total of unused rooms: ", "Each hotel is allowed to contain a maximum of 100 rooms & at least 1 room", 1, 100,hotel);
                    tmpAddress = Utils.updateAddressString("Input new hotel address: ", "Hotel Address is required. Required fields must not be left blank!",hotel);
                    tmpPhone = Utils.updatePhoneWithFormat("New hotel hotline: ", "0\\d{9}", "Hotel phone number is required. Phone number must contain 10 number digits & start with ditgit 0",hotel);
                    tmpRate = Utils.updateRatingChoice("New hotel quality by number of rating stars: ", "Quality rate must be evaluated from (1...6)", 1, 6,hotel);

                    hList.set(i,new HotelInformation(existID, tmpName, tmpRoom, tmpAddress, tmpPhone, tmpRate));
                    
                    System.out.println("An old Hotel is updated with new information sucessfully");
                    confirm = Utils.confirmYesNo("Do you want to continue updating another hotel? (Y/N):  ");

                    if (confirm == false) return;
                }
            }
                if(check==false) {
                    System.out.println("The hotel id you entered does not exist. Please check that you have typed the ID correctly!");
                }
            
        } while (confirm == true);
    }

    public void deleteExistingHotel() {
        if (hList.isEmpty()) {
            System.err.println("There is not any Hotel information for your deleting !");
            System.out.println("\n******************************* Additional Options **********************************************");
            System.out.println("You could choose option 1 to Load Data from file so as to information being appeared for deleting");
            System.out.println("You could choose option 2 to Add new information for a new Hotel");
            System.out.println("You could choose option 3 to Return back to the main menu");
            System.out.println("*************************************************************************************************");
            int subChoice = Utils.getIntegerChoice("Choose your option: ", "Please correct choice's format in range (1 to 3)", 1, 3);
            switch (subChoice) {
                case 1:
                    HotelList.this.loadDataToProgram();
                    return;
                case 2:
                    HotelList.this.addNewHotel();
                    return;
                case 3:
                    System.out.println("\n...Returning to main menu...\n");
                    return;
            }
        }
        boolean check = false;
        String idCheck = Utils.inputString("ID of hotel that you want to delete ?", "This field must not be blank");
        for (HotelInformation existingHotel : hList) {
            if(idCheck.equalsIgnoreCase(existingHotel.getId())) {
                check = true;
                hList.remove(existingHotel);
                System.out.println("Sucessfully delete hotel with id: " + idCheck);
                break;
            }
        }
            if(check == false) {
                System.out.println("The hotel id you entered does not exist. Please check that you have typed the ID correctly!");
            }
        
        
    }

    public void searchHotelInformation() {
        if (hList.isEmpty()) {
            System.err.println("There is not any Hotel information for your Searching !");
            System.out.println("\n******************************** Additional Options **********************************************");
            System.out.println("You could choose option 1 to Load Data from file so as to information being appeared for Searching");
            System.out.println("You could choose option 2 to Add new information for a new Hotel");
            System.out.println("You could choose option 3 to Return back to the main menu");
            System.out.println("**************************************************************************************************");
            int subChoice = Utils.getIntegerChoice("Choose your option: ", "Please correct choice's format in range (1 to 3)", 1, 3);
            switch (subChoice) {
                case 1:
                    HotelList.this.loadDataToProgram();
                    return;
                case 2:
                    HotelList.this.addNewHotel();
                    return;
                case 3:
                    System.out.println("\n...Returning to main menu...\n");
                    return;
            }
        }
        int choice;
        do {
            searchMenu();
//            for (HotelInformation hotelInformation : hList) {
//                
//            }
            //searchMenu();
            choice = Utils.getIntegerChoice("Choose your option: ", "Please correct choice's format in range (1 to 3)", 1, 3);
        } while ( choice < 1 || choice > 3);
        
        boolean check = false;
        boolean subCheck = true;
        
        switch (choice) {
            case 1:
                String idCheck = Utils.inputString("ID of hotel that you want to search ?", "This field must not be blank");
                for (HotelInformation existingHotel : hList) {
                    if (idCheck.equalsIgnoreCase(existingHotel.getId())) {
                        check = true;
                        if(check) {
                            System.out.println("Hotel with ID: " + existingHotel.getId() + " FOUND !");
                            System.out.println("_________________________________________________________________________________________________________________________________________________");
                            System.out.printf("|%-8s|  %-8s  |%20s|                    %-50s| %-11s | %-13s|\n", "Hotel_id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
                            System.out.println("_________________________________________________________________________________________________________________________________________________");
                            System.out.println(existingHotel);
                        }
                        
                        return;
                    }
                }
                if (check == false) {
                    System.out.println("The hotel id you entered does not exist. Please check that you have typed the ID correctly!");
                }
                
                break;
            case 2:
                String addressCheck = Utils.inputString("Address of hotel that you want to search ?", "This field must not be blank");
                for (HotelInformation existingHotel : hList) {
                    if (existingHotel.getAddress().contains(addressCheck)) {
                        check = true;
                        
                        if(subCheck) {
                            System.out.println("Hotel address which contains \""+ addressCheck +"\" FOUND !");
                            System.out.println("_________________________________________________________________________________________________________________________________________________");
                            System.out.printf("|%-8s|  %-8s  |%20s|                    %-50s| %-11s | %-13s|\n", "Hotel_id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
                            System.out.println("_________________________________________________________________________________________________________________________________________________");
                            subCheck = false;
                        }
                        
                        System.out.println(existingHotel);
                        //return;
                    }
                }   
                if (check == false) {
                        System.out.println("The hotel Address you entered does not exist. Please check that you have typed the Address correctly!\"");
                    }
                
                break;
            case 3:
                System.out.println("Quit Searching ... ");
                break;
            default:
                throw new AssertionError();
        }
        
    }

    public void showAllHotel_DescByName() {
        if (hList.isEmpty()) {
            System.err.println("There is not any Hotel information available for Displaying !");
            System.out.println("\n********************************* Additional Options **********************************************");
            System.out.println("You could choose option 1 to Load Data from file so as to information being appeared for Displaying");
            System.out.println("You could choose option 2 to Add new information for a new Hotel");
            System.out.println("You could choose option 3 to Return back to the main menu");
            System.out.println("***************************************************************************************************");
            int subChoice = Utils.getIntegerChoice("Choose your option: ", "Please correct choice's format in range (1 to 3)", 1, 3);
            switch (subChoice) {
                case 1:
                    HotelList.this.loadDataToProgram();
                    return;
                case 2:
                    HotelList.this.addNewHotel();
                    return;
                case 3:
                    System.out.println("\n...Returning to main menu...\n");
                    return;
            }
        }
        
        for (int i = 0; i < hList.size() - 1; i++) {
            for (int j = i+1; j < hList.size(); j++) {
                HotelInformation obj1 = hList.get(i);
                HotelInformation obj2 = hList.get(j);
                
                int num = obj1.compareTo(obj2);
                if (num < 0) { 
//                    HotelInformation tempObject = obj1;
//                    obj1 = obj2;
//                    obj2 = tempObject;
                      hList.set(i, obj2);
                      hList.set(j, obj1);
                    break;
                }
                else if (num > 0) { 
                    break;
                }
                else {
                    break;
                }
//Labda Expression:                
//Collections.sort(hList,(obj1, obj2) -> obj1.getName() - obj2.getName());
            }
        }
        System.out.println("_________________________________________________________________________________________________________________________________________________");
        System.out.printf("|%-8s|  %-8s  |%20s|                    %-50s| %-11s | %-13s|\n", "Hotel_id", "Hotel_Name", "Hotel_Room_Available", "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
        System.out.println("_________________________________________________________________________________________________________________________________________________");
        for (HotelInformation hotel : hList) {
            System.out.println(hotel);
        }
    }

    public void saveDataToRecord() {
        if (hList.isEmpty()) {
            System.err.println("There is nothing in list to save ... Please add new information so that it is available in your list.\n");
        } else {
            Utils.saveToFile(hList, fileName);
            System.out.println("Congratulations! Save data successfully !");
        }
        
    }

    public void leaveProgram() {
        if (!hList.isEmpty()) {
            System.out.println("You have unsaved changes that will be lost if you decide to continue.");
            boolean choice = Utils.confirmYesNo("Confirm surely that you want to leave this program without saving changed data? (Yes/No): ");
            if (choice == true) {
                System.err.println("Data in file has not been changing !");
                System.out.println("Thanks for using this program 💖");
            }
            else {
                Utils.saveToFile(hList, fileName);
                System.err.println("Save your data to file successfully.");
                System.out.println("Thanks for using this program 💖");
                return;
            }
        }
    }
    
    public void searchMenu() {
        ArrayList<String> searchMenu = new ArrayList<>();
        searchMenu.add("6.1. Search by Hotel_Id         [Enter 1]");
        searchMenu.add("6.2. Search by Hotel_Address    [Enter 2]");
        searchMenu.add("6.3. Quit Searching             [Enter 3]");
        for (String string : searchMenu) {
            System.out.println(string);
        }
    }
    
//    public void errHandlingMenu() {
//        ArrayList<String> errorHandlingMenu = new ArrayList<>();
//        errorHandlingMenu.add("");
//    }
    
  
   
    
}
