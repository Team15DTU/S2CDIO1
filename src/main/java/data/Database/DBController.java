package data.Database;

import TUI.TUI;
import data.dao.UserDAO;
import data.dto.UserDTO;

import java.util.Scanner;

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


    public void startup(DBController dbController) {
        startUpSwitchLogic(dbController);

    }


    /*
    --------------------------Private methods--------------------------
     */


    private void SQLDatabase (){
        tui.TUI();

    }

    private void DTODatabase (){
        tui.TUI();

    }

    private void HashmapDatabase () {
        tui.TUI();

    }

    private void startUpSwitchLogic (DBController dbController){
        Scanner input = new Scanner(System.in);

        System.out.println("Choose which database you would like to open:");
        System.out.println("1. SQL-Database");
        System.out.println("2. DTO-Database");
        System.out.println("3. Hashmap-Database");

        switch (input.next()){

            case "1":
                //SQLDatabase startup
                dbController.SQLDatabase();
                break;

            case "2":
                //Ikke persistent DTODatabase startup
                dbController.DTODatabase();
                break;
            case "3":
                //HashmapDatabase startup
                dbController.HashmapDatabase();
                break;
                default:
                    System.out.println("Wrong input, try again");
                    startUpSwitchLogic(dbController);
        }


    }


}
