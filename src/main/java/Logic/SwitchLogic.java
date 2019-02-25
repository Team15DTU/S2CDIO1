package Logic;

import data.dao.UserDAO;
import data.dao.IUserDAO;
import data.dto.UserDTO;

import java.util.Scanner;

public class SwitchLogic {

    // ---------------------- FIELDS ------------------

    private IUserDAO iUserDAO;

    // ---------------------- CONSTRUCTOR --------------------

    public SwitchLogic (IUserDAO iUserDAO) {

        this.iUserDAO = iUserDAO;

    }

    // ------------------- PUBLIC METHODS ----------------

    //Click 1: add user
    public void AddUser() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the user detail of the new user");
        System.out.println("Enter user name");
        String userName = scan.nextLine();
        System.out.println("Enter user ini");
        String ini = scan.nextLine();
        System.out.println("Enter CPR number (fx. 123456-1234)");
        String cpr = scan.nextLine();
        System.out.println("Enter user role");
        String userRole = scan.nextLine();

        int userID;
        try {
            userID = iUserDAO.getUserList().size()+1;
        }catch (IUserDAO.DALException ex){
            System.out.println(ex);
            userID = -1;
        }

        UserDTO user = new UserDTO(userID,userName,ini, cpr,userRole );

        try {
            iUserDAO.createUser(user);
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

    }

    //click 2: print user list

    public void Print() {
        Scanner scan = new Scanner(System.in);
        int p = 0;
        try {
            p = iUserDAO.getUserList().size();
        } catch (IUserDAO.DALException ex) {

            System.out.println(ex);

        }

        for (int i = 0; i < p; i++) {
            try {
                System.out.print(iUserDAO.getUserList().get(i).getUserId()+ ", ");
                System.out.print(iUserDAO.getUserList().get(i).getUserName()+ ", ");
                System.out.print(iUserDAO.getUserList().get(i).getIni()+ ", ");
                System.out.println(iUserDAO.getUserList().get(i).getRoles());
            } catch (IUserDAO.DALException ex) {
                System.out.println(ex);
            }
        }
    }

    //click 3: update user
    public void update() {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter user ID");
            int userID = scan.nextInt();
            UserDTO user = iUserDAO.getUser(userID);
            System.out.println("UserId: "+ user.getUserId());

            //TODO: Burde muligvis være sin egen menu .
            System.out.println("Current UserName: "+ user.getUserName());
            System.out.println("Enter new UserName below and press Enter:");
            String newUserName = scan.next();
            user.setUserName(newUserName);

            iUserDAO.updateUser(user);
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }

    //click 4: delete user
    public void delete() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the user ID of the user you want to delete");
        int choice = scan.nextInt();
        try {
            System.out.println("Are you sure you want to delete: " + iUserDAO.getUser(choice).getUserName() + "?");
            System.out.println("Click 1 to enter, click 2 to skip");
            int choice2 = scan.nextInt();
            if (choice2 == 1) {
                iUserDAO.deleteUser(choice);
            } else {
                System.out.println(iUserDAO.getUser(choice).getUserName() + "didn't get deleted.");
            }

        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }

    // click 6: Check password
    public void checkPassword (UserDAO dao) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write index of the user you want to check password on");
        int nr = scan.nextInt();
        try {
            System.out.println(dao.getUser(nr).getPassword());
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }

    // Click 5 : Shutdown

    public void shutdown () {

        try {
            iUserDAO.shutdown();
        } catch (IUserDAO.DALException e) {
            e.printStackTrace();
        }
    }


    // ------------------- SUPPORT METHODS -------------------------

}