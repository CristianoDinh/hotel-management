
package user;

import data.HotelList;
import java.util.ArrayList;
import utils.Utils;

/**
 *
 * @author Lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> menu = new ArrayList<>();
        
        int choice;
        boolean flag = false;
        HotelList hotelMethod = new HotelList();
        do {
            System.out.println("\n======= WELCOME TO HOTEL MANAGEMENT PROGRAM =======");
            System.out.println("1. Load data from file to program");
            System.out.println("2. Add new hotel");
            System.out.println("3. Check to exist hotel");
            System.out.println("4. Update hotel information");
            System.out.println("5. Delete hotel");
            System.out.println("6. Search hotel");
            System.out.println("7. Displaying a hotel list (descending by Hotel_Name) ");
            System.out.println("8. Save to file ");
            System.out.println("9. Quit program ");

        
            
            choice = Utils.getIntegerChoice("Enter choice that you want to use: ", "Please correct choice's format in range (1 to 9)", 1, 9);
            switch(choice) {
                case 1:
                    hotelMethod.loadDataToProgram();
                    break;
                case 2:
                    hotelMethod.addNewHotel();
                    break;
                case 3:
                    hotelMethod.verifyHotel();
                    break;
                case 4:
                    hotelMethod.updateExsitingHotel();
                    break;
                case 5:
                    hotelMethod.deleteExistingHotel();
                    break;
                case 6:
//                    for ( String x : searchMenu ) {
//                        System.out.println(x);
//                    }    
                    hotelMethod.searchHotelInformation();
                    break;
                case 7:
                    hotelMethod.showAllHotel_DescByName();
                    break;
                case 8:
                    hotelMethod.saveDataToRecord();
                    //hotelMethod.arrageAscByRoom();
                    break;
                case 9:
                    hotelMethod.leaveProgram();
                    flag = true;
                    break;  
 
            }
        } while (flag == false);
        

        
        
        
    }
    
}
