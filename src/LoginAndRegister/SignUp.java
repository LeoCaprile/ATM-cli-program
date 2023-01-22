package LoginAndRegister;

import Services.UserServices;
import Utilities.Utilities;

import java.sql.SQLException;

public class SignUp {
    Utilities utils = new Utilities();
    UserServices userModel = new UserServices();

    String user;
    String password;
    public SignUp() throws SQLException {
        utils.flushConsole();
        utils.print("--------REGISTRO--------");
        utils.print("Introduzca su usuario");
        user = utils.getUserInput();
        utils.print("Introduzca su contraseña");
        password = utils.getUserInput();

        try {
            userModel.RegisterUser(user, password);
            utils.print("Usuario creado con exito!");
            new Menu();
        } catch(SQLException e){
            utils.print(String.valueOf(e));
            utils.print("Ha ocurrido un error al crear el usuario, porfavor, vuelva a intentarlo");
            new Menu();
        }
    }

}
