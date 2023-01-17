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
            utils.print("Introduzca su usuario");
            user = utils.getUserInput();
            utils.print("Introduzca su contraseña");
            password = utils.getUserInput();

            findedUser = UserServices.GetUser(user);

            if(findedUser.username == null){
                utils.print("La contraseña o el nombre de usuario es incorrecto");
                continue;
            }

            if(findedUser.username.equals(user) && findedUser.password.equals(password)){
                utils.print("Ha ingresado correctamente");
                isLogged = true;
                new UserMenu();

            }else {
                utils.print("La contraseña o el nombre de usuario es incorrecto");
            }
        }

    }
}
