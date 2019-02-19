package Logic;

import dal.UserDAO;
import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.Scanner;

public class SwitchLogic {

    //Click 1:
    public static void AddUserLogic(UserDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("You entered 1, to add a new user");
        System.out.println("Enter userID");
        int userID = scan.nextInt();
        scan.nextLine(); //gør så den ikke hopper over "enter user name" inputtet !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        System.out.println("Enter user name");
        String userName = scan.nextLine();
        System.out.println("Enter user ini");
        String ini = scan.nextLine();
        System.out.println("Enter CPR number");
        String cPR = scan.nextLine();
        System.out.println("Enter user role");
        String userRole = scan.nextLine();
        UserDTO user = new UserDTO(userID,userName,ini, cPR, "Password",userRole );
        try {
            dao.createUser(user);
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

    }

    public static void Print(UserDAO dao) {
        Scanner scan = new Scanner(System.in);

        try {
            System.out.println(dao.getUserList().get(0).getUserId());
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

    }





}
