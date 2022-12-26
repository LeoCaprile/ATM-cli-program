package LoginAndRegister;

import Utilities.Utilities;

import java.sql.SQLException;

public class Login {
    String user;
    String password;
    Boolean isLogged = false;

    Utilities utils = new Utilities();

    public Login() throws SQLException {

        while(!isLogged){
            utils.print("Introduzca su usuario");
            user = utils.getUserInput();
            utils.print("Introduzca su contraseña");
            password = utils.getUserInput();

            if(user.equals("pablo") && password.equals("123456")){
                utils.print("Ha ingresado correctamente");
                isLogged = true;
                new Menu();
            }else {
                utils.print("La contraseña o el nombre de usuario es incorrecto");
            }
        }

    }
}
