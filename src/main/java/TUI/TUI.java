package TUI;

import Logic.SwitchLogic;
import data.dao.IUserDAO;

import java.util.Scanner;

public class TUI {

    // ------------------------ Fields -------------------------

    IUserDAO DB_interface;

    // ------------------------- Constructor --------------------

    public TUI (IUserDAO userDAO) {

        DB_interface = userDAO;

    }

    // -------------------- Public Method ------------------------
    public void TUI() {

        while (true) {
            Scanner scan = new Scanner(System.in);
            startMenu();
            String choice = scan.next();
            TheSwitch(choice);
        }
    }

    // ---------------------- Private Method ------------------------
    // Menu messages

    private void startMenu() {
        System.out.println();
        System.out.println("Enter a number for which action you want to take");
        System.out.println("1. Add new user");
        System.out.println("2. Show users");
        System.out.println("3. Update user");
        System.out.println("4. Remove user");
        System.out.println("5. Close the program (Saves Data)");
        System.out.println("6. Check password of a user");
    }

    /**
     *  The main switch statement, that lets the user choose between 6 actions regarding the user.
     */


    private void TheSwitch(String choice) {

        SwitchLogic SL = new SwitchLogic(DB_interface);

        switch (choice) {
            // 1. Add New User.
            case "1":
                SL.AddUser();
                break;

            // 2. Show Users.
            case "2":
                SL.showUsers();
                break;

            // 3. Update User.
            case "3":
                SL.update();
                break;

            // 4. Delete User.
            case "4":
                SL.delete();
                break;

            // 5. Close the program.
            case "5":
                SL.shutdown();
                System.exit(0);
                break;

            // 6. Check Password for a User.
            case "6":
                SL.checkPassword();
                break;

            default:
                System.out.println("Please enter a valid input");
                System.out.println();
        }

    }

}
