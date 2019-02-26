package data.dao;

import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO implements IUserDAO {

    // ----------------- FIELDS ---------------

    List<UserDTO> userDTOList;

    // ------------- CONSTRUCTOR -----------------

    public UserDAO () {
        userDTOList = new ArrayList<>();
    }

    // -------------- Getters & Setters ------------------

    // region Getters and Setters

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    // endregion

    // ----------------- PUBLIC METHOD -------------------------
    @Override
    public UserDTO getUser(int userId) throws DALException {

        UserDTO user;

        for (int i = 0; i<userDTOList.size(); i++) {
            if (userId == userDTOList.get(i).getUserId()) {
                user = userDTOList.get(i);
                return user;
            }
        }

        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {

        return userDTOList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

        userDTOList.add(user);

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Click 1 to enter, click 2 to skip");

        // ID (mabey remove it, coss wierd ID is imposible to find in getuser()
        System.out.println("Want to change User ID?");
        int choice1 = scan.nextInt();
        if (choice1 == 1) {
            System.out.println("enter the new user ID");
            int newUserID = scan.nextInt();
            user.setUserId(newUserID);
        }
        scan.nextLine();

        //User name
        System.out.println("Want to change User Name?");
        int choice2 = scan.nextInt();
        scan.nextLine();
        if (choice2 == 1) {
            System.out.println("enter the new user name");
            String newUserName = scan.nextLine();
            user.setUserName(newUserName);
        }


        //User initials
        System.out.println("Want to change initials?");
        int choice3 = scan.nextInt();
        scan.nextLine();
        if (choice3 == 1) {
            System.out.println("enter the new initials");
            String newUserIni = scan.nextLine();
            user.setIni(newUserIni);
        }

        //User CPR
        System.out.println("Want to change CPR number?");
        int choice4 = scan.nextInt();
        scan.nextLine();
        if (choice4 == 1) {
            System.out.println("enter the new CPR number");
            String newCPR = scan.nextLine();
            user.setCpr(newCPR);
        }

        //User password
        System.out.println("Want to change password?");
        int choice5 = scan.nextInt();
        scan.nextLine();
        if (choice5 == 1) {
            System.out.println("enter the new password");
            String newPassword = scan.nextLine();
            user.setPassword(newPassword);
        }

    }


    @Override
    public void deleteUser(int userId) throws DALException {

        List userList = getUserList();
        for (int i = 0; i<userList.size(); i++) {
            if (getUser(i).getUserId() == userId) {
                userDTOList.remove(i);
            }
        }

    }

    public void shutdown () {

    }







}
