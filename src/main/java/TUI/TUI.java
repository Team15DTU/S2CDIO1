package TUI;

import Logic.SwitchLogic;
import dal.UserDAO;
import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TUI {

    // ------------------------ Fields -------------------------

    UserDAO dao;

    // ------------------------- Constructor --------------------

    public TUI () {

        dao = new UserDAO();

    }

    // -------------------- Public Method ------------------------
    public void TUI() {

        while (true) {
            Scanner scan = new Scanner(System.in);
            MenuBesked();
            int choice = scan.nextInt();
            TheSwitch(dao, choice);

        }

    }


    // ---------------------- Private Method ------------------------
    // Menu beskederne

    private void MenuBesked() {
        System.out.println();
        System.out.println("Enter a number for which action you want to take");
        System.out.println("1. Add new user");
        System.out.println("2. Show users");
        System.out.println("3. Opdate user");
        System.out.println("4. Remove user");
        System.out.println("5. Close the program");
        System.out.println("6. check password of a user");
    }

    //Menu switchen

    private void TheSwitch(UserDAO dao, int choice) {

        SwitchLogic SL = new SwitchLogic();


        switch (choice) {
            case 1:
                SL.AddUserLogic(dao);
                break;
            case 2: //some thing
                SL.Print(dao);
                break;
            case 3: //some thing
                try {
                   Scanner scan = new Scanner(System.in);
                   System.out.println("Enter user ID");
                   int userID = scan.nextInt();
                   UserDTO user = dao.getUser(userID);
                   SL.update(dao, user);
                } catch (IUserDAO.DALException ex) {
                    System.out.println(ex);
                }
                break;
            case 4: //some thing
                SL.delete(dao);
                break;
            case 5:
                System.exit(0);
                break;
            case 6:
                Scanner scan = new Scanner(System.in);
                System.out.println("Write index of the user you want to check password on");
                int nr = scan.nextInt();
                try {
                    System.out.println(dao.getUser(nr).getPassword());
                }
                catch (IUserDAO.DALException ex) {
                    System.out.println(ex);
                }
                break;
            default:
                System.out.println("Please enter a valid input");
                System.out.println();
        }

    }

}
