package Logic;

import data.File.FileDBController;
import data.dao.IUserDAO;
import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SwitchLogic {

    // ---------------------- FIELDS ------------------

    private IUserDAO iUserDAO;

    // ---------------------- CONSTRUCTOR --------------------

    public SwitchLogic (IUserDAO iUserDAO) {

        this.iUserDAO = iUserDAO;

    }

    // ------------------- PUBLIC METHODS ----------------

    //Click 1: Create a user
    public void createUser() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the user detail of the new user");
        System.out.println("Enter user name");
        String userName = scan.nextLine();
        System.out.println("Enter user ini");
        String ini = scan.nextLine();
        System.out.println("Enter CPR number (format: 123456-1234)");
        String cpr = scan.nextLine();
        while (!cprChecker(cpr)) {
            System.out.println("The entered cpr number DON'T match the format \"123456-1234\". Try again");
            cpr = scan.nextLine();
        }
        System.out.println("Enter user role");
        String userRole = scan.nextLine();

        int userID;
        try {
            if (iUserDAO.getUserList().size()==0) {
                userID = 1;
            } else {
                int lastIndex = iUserDAO.getUserList().size()-1;
                userID = iUserDAO.getUserList().get(lastIndex).getUserId()+1;
            }

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

    public void showUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();

        try {
            userDTOList = iUserDAO.getUserList();
        } catch (IUserDAO.DALException ex) {
            System.out.println(ex);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ ALL USERS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (UserDTO tempUser : userDTOList) {
            System.out.println("UserID: "+tempUser.getUserId() + " UserName: " + tempUser.getUserName() +
                    " Ini: " + tempUser.getIni() + " Cpr: " + tempUser.getCpr() +
                    " Password: " + tempUser.getPassword() + " Role: " +tempUser.getRoles());
        }

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //click 3: update user
    public void update() {
        Scanner scan = new Scanner(System.in);
        UserDTO userDTOToUpdate;

        String menuSelector;

        try {
            System.out.println("Enter userID of User you wishes to update information on:");
            int userIDToUpdate = scan.nextInt();
            userDTOToUpdate = iUserDAO.getUser(userIDToUpdate);


            do {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ UPDATE USER ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                System.out.println("Users information to update: "+ userDTOToUpdate.getUserName() + "\n");
                System.out.println("Press 1: Update UserName \t\t\tCurrent UserName: " + userDTOToUpdate.getUserName());
                System.out.println("Press 2: Update Initials \t\t\tCurrent Initials: " + userDTOToUpdate.getIni());
                System.out.println("Press 3: Update Roles    \t\t\tCurrent Roles:    " + userDTOToUpdate.getRoles());
                System.out.println("Press 4: Update Password \t\t\tCurrent Password: " + userDTOToUpdate.getPassword());
                System.out.println("\nPress 5: To go back to main menu\n");
                menuSelector = scan.next();
                updateActionSwitch(userDTOToUpdate,menuSelector);
                // Updates user
                iUserDAO.updateUser(userDTOToUpdate);

            } while (!menuSelector.equals("5"));



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
        String userNameOnUserToCheck = null;

        int[] noOfCharsPassed = new int[3];

        try {

            UserDTO userDTOToCheck = iUserDAO.getUser(userIDToCheck);
            userNameOnUserToCheck = userDTOToCheck.getUserName();
            noOfCharsPassed = userDTOToCheck.checkAndEachTypeOfCharInPassword(userDTOToCheck.getPassword());

        } catch (IUserDAO.DALException e) {
            System.out.println(e);
        }

        if (noOfCharsPassed[0] != 0 && noOfCharsPassed[1] != 0 && noOfCharsPassed[2] != 0) {
            System.out.println(userNameOnUserToCheck + "'s password is VALID");
            System.out.println("Password contain uppercase letters [✓]");
            System.out.println("Password contain lowercase letters [✓]");
            System.out.println("Password contain numbers letters   [✓]");
        } else {
            System.out.println(userNameOnUserToCheck + "'s password is INVALID");

            // Check for numbers
            if (noOfCharsPassed[0] == 0) {
                System.out.println("Password contain numbers           [✗]");
            } else {
                System.out.println("Password contain numbers           [✓]");
            }

            // Check for Uppercase letters
            if (noOfCharsPassed[1] == 0) {
                System.out.println("Password contain uppercase letters [✗]");
            } else {
                System.out.println("Password contain uppercase letters [✓]");
            }

            // Check for LowerCase letters
            if (noOfCharsPassed[2] == 0) {
                System.out.println("Password contain lowercase letters [✗]");
            } else {
                System.out.println("Password contain lowercase letters [✓]");
            }
        }
    }

    // Click 5 : Shutdown

    public void shutdown () {

        if (iUserDAO instanceof FileDBController) {

            ((FileDBController) iUserDAO).shutdown();

        }

        System.out.println("\n Bye Bye...");

    }


    // ------------------- SUPPORT METHODS -------------------------

    private void updateActionSwitch (UserDTO userDTO, String menuSelector) {

        Scanner scanner = new Scanner(System.in);

        switch (menuSelector) {

            // 1: Update UserName.
            case "1" :
                System.out.println("Enter the UserName you wishes to change to: (no spaces)");
                String newUserName = scanner.next();
                userDTO.setUserName(newUserName);
                System.out.println("UserName is updated to: " + userDTO.getUserName());
                break;

            // 2: Update Initials.
            case "2" :
                System.out.println("Enter the Initials you wishes to change to:");
                String newInitials = scanner.next();
                userDTO.setIni(newInitials.toUpperCase());
                System.out.println("Initials is updated to: " + userDTO.getIni());
                break;

            // 3: Update Roles.
            case "3":
                System.out.println("Press 1: To add a new role.");
                System.out.println("Press 2: To delete a role.");
                String roleMenuSelector = scanner.next();
                roleUpdateAction(userDTO,roleMenuSelector);
                break;

            // 4: Update Password.
            case "4":
                System.out.println("Enter the Password you wishes to change to:");
                String newPassword = scanner.next();
                userDTO.setPassword(newPassword);
                System.out.println("Password is updated to: " + newPassword);
                break;

            case "5":
                break;

            default:
                break;
        }
    }

    private void roleUpdateAction (UserDTO userDTO, String rolesMenuSelected) {

        Scanner scanner = new Scanner(System.in);

        switch (rolesMenuSelected) {

            // Add Role
            case "1":
                System.out.println("Enter name on Role that you wishes to add, (Current roles: " + userDTO.getRoles() +").");
                String addRole = scanner.next();
                userDTO.addRole(addRole);
                System.out.println(addRole + " have been added to " + userDTO.getUserName() + "'s list of roles");
                break;

            // Delete Role
            case "2":
                System.out.println("Enter name on role that you wishes to delete, (Current roles: " + userDTO.getRoles() + ").");
                String deleteRole = scanner.next();
                userDTO.removeRole(deleteRole);
                System.out.println(deleteRole + " have been deleted from " + userDTO.getUserName() + "'s list of roles");
                break;

            default:
                System.out.println("Wrong input in method roleUpdateAction() in SwitchLogic.java");
                break;
        }
    }

    private boolean cprChecker (String cpr) {

        if (cpr.length() == 11 ) {
            if (((int) cpr.charAt(6) == ((int) '-'))) {
                for (int i = 0; i < 6 ; i++) {
                    if (((int) cpr.charAt(i)) < ((int) '0') || ((int) cpr.charAt(i)) > ((int) '9')) {
                        return false;
                    }
                }
                for (int i = 7; i < 11 ; i++) {
                    if (((int) cpr.charAt(i)) < ((int) '0') || ((int) cpr.charAt(i)) > ((int) '9')) {
                        return false;
                    }
                }
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }
    }

}