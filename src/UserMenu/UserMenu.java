package UserMenu;

import User.User;
import Utilities.Utilities;

public class UserMenu {
    User loggedUser;
    Utilities utils = new Utilities();
    String optionSelected;

    Boolean logout = false;
    public UserMenu(User user){
        loggedUser = user;
        while(!logout) {
            utils.print("WELCOME TO THE BANKING SYSTEM, SELECT AN OPERATION");
            utils.print("1. DEPOSIT");
            utils.print("2. WITHDRAW");
            utils.print("3. CHECK BALANCE");
            utils.print("4. ACCOUNT INFO");
            utils.print("5. EXIT");

            optionSelected = utils.getUserInput();

            switch (optionSelected) {
                case "1" -> {
                    utils.print("a");
                }
                case "2" -> {
                    utils.print("b");
                }
                case "3" -> {
                    utils.print("c");
                }
                case "4" -> {
                    if (loggedUser != null) {
                        utils.print("USERNAME: " + loggedUser.username);
                        utils.print("CREATED AT: " + loggedUser.createdOn.toString());
                        utils.print("LAST TRANSACTION: " +
                                (this.loggedUser.lastTransaction != null
                                        ? loggedUser.lastTransaction.toString()
                                        : "HAVEN'T DID ANY TRANSACTION"));
                        utils.print("--------------Press ENTER to go back--------------");
                        utils.getUserInput();
                    }
                }
                case "5" -> {
                    utils.print("d");
                }
            }
        }
    }

}
