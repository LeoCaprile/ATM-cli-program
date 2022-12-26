package LoginAndRegister;

import Utilities.Utilities;

import java.sql.SQLException;

public class Menu {
    Utilities utils = new Utilities();
    
    String selectedOption;


    public Menu() throws SQLException {
        utils.print("Hola bienvenido, Eliga una opción para continuar");
        utils.print("1. Ingresar");
        utils.print("2. Registrarse");
        
        selectedOption = utils.getUserInput();

        switch (selectedOption) {
            case "1" -> new Login();
            case "2" -> new Register();
            default -> {
                utils.print("Eliga una opción valida.");
                new Menu();
            }
        }
        
    }
}
