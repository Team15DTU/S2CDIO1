package data.Database;

import TUI.TUI;
import data.dao.IUserDAO;
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

    IUserDAO DB;

    /*
    --------------------------Contructor--------------------------
     */

    public DBController(){

    }


    /*
    -------------------------- Getters & Setters ---------------------
     */

    // region Getters & Setters

    public IUserDAO getDB() {
        return DB;
    }

    public void setDB(IUserDAO DB) {
        this.DB = DB;
    }

    // endregion

    /*
    --------------------------Public methods--------------------------
     */

    public void startUpAndDBSelector (){
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Choose which database you would like to use:");
            System.out.println("1. DAO-Database (Don't(!) saves data)");
            System.out.println("2. File-Database (Saves data)");
            System.out.println("3. SQL-Database (Saves data)");
            System.out.print("Your choice: ");

            choice = input.nextInt();
        }
        while (!DBSwitch(choice));
    }

    /*
    --------------------------Private methods--------------------------
     */


    private void SQLDatabase (){
        // TODO: Set DB to the correct SQL_DB class



        System.out.println("Your selected SQL as Database type.");

    }

    private void DAODatabase (){
        // TODO: Set DB to the correct DAO_DB class

        DB = new UserDAO();

        System.out.println("You selected DAO as Database type.");

    }

    private void FileDatabase () {
        // TODO: Set DB to the correct File_DB class



        System.out.println("You selected File as Database type.");
    }


    private boolean DBSwitch (int menuChoice) {

        switch (menuChoice){

            // DAO-Database (ArrayList)
            case 1:
                DAODatabase();
                return true;

            // File-Database (HashMap % CSV-File)
            case 2:
                FileDatabase();
                return true;

            // SQL-Database
            case 3:
                SQLDatabase();
                return true;

            default:
                System.out.println("Wrong input, try again");
                return false;
        }

    }


}
