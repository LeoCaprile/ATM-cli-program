package UserMenu;

import LoginAndRegister.Menu;
import Services.UserServices;
import User.User;
import Utilities.Utilities;

import java.sql.SQLException;

public class UserMenu {
    User loggedUser;
    Utilities utils = new Utilities();
    String optionSelected;

    UserServices userServices = new UserServices();
    Boolean logout = false;
    public UserMenu(User user) throws SQLException {
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
                    utils.print("ENTER THE QUANTITY OF MONEY YOU WANT TO DEPOSIT");
                    String money = utils.getUserInput();
                    String totalOfMoney = String.valueOf((loggedUser.money+Integer.parseInt(money)));
                    userServices.UpdateUserMoneyById(user.userId, totalOfMoney);
                    loggedUser = userServices.GetUser(user.username);
                    utils.print("TRANSACTION CORRECT");
                }
                case "2" -> {
                    utils.print("ENTER THE QUANTITY OF MONEY YOU WANT TO WITHDRAW");
                    String money = utils.getUserInput();
                    String totalOfMoney = String.valueOf((loggedUser.money-Integer.parseInt(money)));
                    userServices.UpdateUserMoneyById(user.userId, totalOfMoney);
                    loggedUser = userServices.GetUser(user.username);
                    utils.print("TRANSACTION CORRECT");
                }
                case "3" -> {
                   if(loggedUser != null){
                       utils.print("-----------------YOUR BALANCE IS------------------");
                       utils.print("--------------$"+loggedUser.money+"---------------");
                       utils.print("--------------Press ENTER to go back--------------");
                       utils.getUserInput();
                   }
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
                    logout = true;
                    new Menu();
                }
            }
        }
    }

}
