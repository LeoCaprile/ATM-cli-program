package Menu;

import Utilities.Utilities;

public class Menu {

    Utilities utils = new Utilities();
    String optionSelected;
    public Menu (){
        utils.print("Bienvenido al sistema bancario que desea hacer?");
        utils.print("1. Depositar");
        utils.print("2. Retirar");
        utils.print("3. Consultar");
        utils.print("4. Salir");

        optionSelected = utils.getUserInput();

    }

}
