package LoginAndRegister;

import Utilities.Utilities;

import java.sql.SQLException;

public class Menu {
    Utilities utils = new Utilities();
    
    String selectedOption;


    public Menu() throws SQLException {
        utils.print("Hi! Welcome to the banking system, select an option.");
        utils.print("1. Login");
        utils.print("2. SignUp");
        
        selectedOption = utils.getUserInput();

        switch (selectedOption) {
            case "1" -> new Login();
            case "2" -> new SignUp();
            default -> {
                utils.print("Select a valid option.");
                new Menu();
            }
        }
        
    }
}
