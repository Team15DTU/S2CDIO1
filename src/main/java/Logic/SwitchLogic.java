package Logic;

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
        Scanner scan = new Scanner(System.in);
        UserDTO userDTOToUpdate;

        int menuSelector;

        try {
            System.out.println("Enter user ID");
            int userIDToUpdate = scan.nextInt();
            userDTOToUpdate = iUserDAO.getUser(userIDToUpdate);
            System.out.println("User to update: "+ userDTOToUpdate.getUserName());

            do {
                System.out.println("Press 1: Update UserName \t\t\tCurrent UserName: " + userDTOToUpdate.getUserName());
                System.out.println("Press 2: Update Initials \t\t\tCurrent Initials: " + userDTOToUpdate.getIni());
                System.out.println("Press 3: Update Roles    \t\t\tCurrent Roles:    " + userDTOToUpdate.getRoles());
                System.out.println("Press 4: Update Password \t\t\tCurrent Password  " + userDTOToUpdate.getPassword());

                menuSelector = scan.nextInt();
                updateActionSwitch(userDTOToUpdate,menuSelector);

            } while (menuSelector >=1 && menuSelector <= 4);

            iUserDAO.updateUser(userDTOToUpdate);

        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }

    //click 4: delete user
    public void delete() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the user ID of the user you want to delete");
        int choice = scan.nextInt();
        String userNameToDelete;
        try {
            userNameToDelete = iUserDAO.getUser(choice).getUserName();
            System.out.println("Are you sure you want to delete: " + userNameToDelete + "? (Y/N)");
            String yesOrNo = scan.next();
            if (yesOrNo.toUpperCase().equals("Y")) {
                iUserDAO.deleteUser(choice);
                System.out.println(userNameToDelete+ " DID get deleted");
            } else {
                System.out.println(iUserDAO.getUser(choice).getUserName() + " DIDN'T get deleted.");
            }

        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }
    }

    // click 6: Check password
    public void checkPassword () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Write UserID of the User who's password you want to check.");
        int userIDToCheck = scan.nextInt();
        try {
            UserDTO userToCheck = iUserDAO.getUser(userIDToCheck);
            if (userToCheck.passwordChecker()) {
                System.out.println("The User: " + userToCheck.getUserName() + " is VALID");
            } else {
                System.out.println("The User: " + userToCheck.getUserName() + " is INVALID");
            }
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

    private void updateActionSwitch (UserDTO userDTO, int menuSelector) {

        Scanner scanner = new Scanner(System.in);

        switch (menuSelector) {

            // 1: Update UserName.
            case 1 :
                System.out.println("Enter the UserName you wishes to change to: (no spaces)");
                String newUserName = scanner.next();
                userDTO.setUserName(newUserName);
                System.out.println("UserName is updated to: " + userDTO.getUserName());
                break;

            // 2: Update Initials.
            case 2 :
                System.out.println("Enter the Initials you wishes to change to:");
                String newInitials = scanner.next();
                userDTO.setIni(newInitials.toUpperCase());
                System.out.println("Initials is updated to: " + userDTO.getIni());
                break;

            // 3: Update Roles.
            case 3:
                System.out.println("Press 1: To add a new role.");
                System.out.println("Press 2: TO delete a role.");
                int roleMenuSelector = scanner.nextInt();
                roleUpdateAction(userDTO,roleMenuSelector);
                break;

            // 4: Update Password.
            case 4:
                System.out.println("Enter the Password you wishes to change to:");
                String newPassword = scanner.next();
                userDTO.setPassword(newPassword);
                System.out.println("Password is updated to: " + newPassword);
                break;

            default:
                break;
        }
    }

    private void roleUpdateAction (UserDTO userDTO, int rolesMenuSelected) {

        Scanner scanner = new Scanner(System.in);

        switch (rolesMenuSelected) {

            // Add Role
            case 1:
                System.out.println("Enter name on Role that you wishes to add, (Current roles: " + userDTO.getRoles() +").");
                String addRole = scanner.next();
                userDTO.addRole(addRole);
                System.out.println(addRole + " have been added to " + userDTO.getUserName() + "'s list of roles");

            // Delete Role
            case 2:
                System.out.println("Enter name on role that you wishes to delete, (Current roles: " + userDTO.getRoles() + ").");
                String deleteRole = scanner.next();
                userDTO.addRole(deleteRole);
                System.out.println(deleteRole + " have been deleted from " + userDTO.getUserName() + "'s list of roles");

            default:
                System.out.println("Wrong input in method roleUpdateAction() in SwitchLogic.java");
                break;
        }
    }

}