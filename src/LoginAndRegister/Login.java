package LoginAndRegister;

import Services.UserServices;
import User.User;
import UserMenu.UserMenu;
import Utilities.Utilities;

import java.sql.SQLException;

public class Login {
    String user;
    String password;
    Boolean isLogged = false;
    User findedUser;
    Utilities utils = new Utilities();
    UserServices UserServices = new UserServices();

    public Login() throws SQLException {

        while(!isLogged){
            utils.print("Enter your username");
            user = utils.getUserInput();
            utils.print("Enter your password");
            password = utils.getUserInput();

            findedUser = UserServices.GetUser(user);

            if(findedUser.username == null){
                utils.print("The username or password is incorrect, try again");
                continue;
            }

            if(findedUser.username.equals(user) && findedUser.password.equals(password)){
                isLogged = true;
                new UserMenu(findedUser);

            }else {
                utils.print("The username or password is incorrect, try again");
            }
        }

    }
}
