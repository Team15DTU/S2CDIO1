package TUI;

import Logic.SwitchLogic;
import data.dao.UserDAO;
import data.dao.IUserDAO;
import data.dto.UserDTO;

import java.util.Scanner;

public class TUI {

    // ------------------------ Fields -------------------------

    UserDAO dao;
    UserDTO dto;

    // ------------------------- Constructor --------------------

    public TUI () {

        dao = new UserDAO();
        dto = new UserDTO();

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
        System.out.println("3. Update user");
        System.out.println("4. Remove user");
        System.out.println("5. Close the program");
        System.out.println("6. Check password of a user");
    }

    //Menu switchen

    private void TheSwitch(UserDAO dao, int choice) {

        SwitchLogic SL = new SwitchLogic();


        switch (choice) {
            case 1:
                //Add User
                SL.AddUserLogic(dao);
                break;

            case 2:
                //Show users
                SL.Print(dao);
                break;

            case 3:
                //Update user
                try {
                    SL.update(dao, dto);
                } catch (IUserDAO.DALException e) {
                    e.printStackTrace();
                }
                break;

            case 4:
                //Delete user
                SL.delete(dao);
                break;

            case 5:
                //Close program
                System.exit(0);
                break;

            case 6:
                //Check password
                SL.checkPassword(dao);
                break;

            default:
                System.out.println("Please enter a valid input");
                System.out.println();
        }

    }

}
