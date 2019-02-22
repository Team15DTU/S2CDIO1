package data.Database;

import TUI.TUI;
import dal.UserDAO;
import data.dto.UserDTO;

/**
 * @author Nikolaj Wassmann
 */

public class DBController {

    /*
    ----------------------------Fields-------------------------
     */

    UserDAO userDAO;
    UserDTO userDTO;
    TUI tui;



    /*
    --------------------------Contructor--------------------------
     */


    public DBController(){
        userDAO = new UserDAO();
        userDTO = new UserDTO();
        tui = new TUI();
    }



    /*
    --------------------------Public methods--------------------------
     */

    /**
     * Vi skal have modificeret TUI så den æn
     */

    public void SQLDatabase (){
        tui.TUI();

    }

    public void DTODatabase (){
        tui.TUI();

    }

    public void HashmapDatabase () {

    }



    /*
    --------------------------Private methods--------------------------
     */


}
