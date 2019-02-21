package Logic;

import dal.UserDAO;
import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class SwitchLogic {

    //Click 1: add user
    public void AddUserLogic(UserDAO dao) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the user detail of the new user");
        System.out.println("Enter user name");
        String userName = scan.nextLine();
        System.out.println("Enter user ini");
        String ini = scan.nextLine();
        System.out.println("Enter CPR number");
        String cPR = scan.nextLine();
        System.out.println("Enter user role");
        String userRole = scan.nextLine();

        int userID;
        try {
            userID = dao.getUserList().size();
        }catch (IUserDAO.DALException ex){
            System.out.println(ex);
            userID = -1;
        }
        UserDTO user = new UserDTO(userID,userName,ini, cPR, "Password",userRole );
        try {
            dao.createUser(user);
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

    }

    //click 2: print user list
    public void Print(UserDAO dao) {
        Scanner scan = new Scanner(System.in);
        int p = 0;
        try {
            p = dao.getUserList().size();
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

        for (int i = 0; i < p; i++) {
            try {
                System.out.print(dao.getUserList().get(i).getUserId()+ ", ");
                System.out.print(dao.getUserList().get(i).getUserName()+ ", ");
                System.out.print(dao.getUserList().get(i).getIni()+ ", ");
                System.out.println(dao.getUserList().get(i).getRoles());
            } catch (IUserDAO.DALException ex) {
                System.out.println(ex);
            }
        }
    }

    //click 3: update user
    public void update(UserDAO dao, UserDTO user) {

       try { dao.updateUser(user);}
       catch (IUserDAO.DALException ex) { System.out.println(ex); }


    }

    //click 4: delete user
    public void delete(UserDAO dao) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the user ID of the user you want to delete");
        int choice = scan.nextInt();
        try {
            for (int i = 0; i<dao.getUserList().size(); i++) {
                if (choice == dao.getUser(i).getUserId()){
                    System.out.println("Are you sure you want to delete " + dao.getUser(i).getUserName() + "?");
                    System.out.println("Click 1 to enter, click 2 to skip");
                    int choice2 = scan.nextInt();
                    if (choice2 == 1) {
                        int id = dao.getUser(i).getUserId();
                        dao.deleteUser(id);
                    }
                }
            }
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }







}