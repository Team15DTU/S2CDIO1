package data.Database;

import data.File.FileDBController;
import data.conn.SQL_DB;
import data.dao.IUserDAO;
import data.dao.UserDAO;

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
        // TODO: Set SQL_DB to the correct SQL_DB class

        DB = new SQL_DB();

        System.out.println("Your selected SQL as Database type.");

    }

    // CHOOSE: 1
    private void DAODatabase (){
        // TODO: Set SQL_DB to the correct DAO_DB class

        DB = new UserDAO();

        System.out.println("You selected DAO as Database type.");

    }

    private void FileDatabase () {
        // TODO: Set SQL_DB to the correct File_DB class

        DB = new FileDBController();

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
