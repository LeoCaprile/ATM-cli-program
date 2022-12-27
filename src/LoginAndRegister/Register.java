package LoginAndRegister;

import Model.UserModel;
import Utilities.Utilities;

import java.sql.SQLException;

public class Register {
    Utilities utils = new Utilities();
    UserModel userModel = new UserModel();

    String user;
    String password;
    public Register() throws SQLException {
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
