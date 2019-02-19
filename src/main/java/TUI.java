import dto.UserDTO;

import java.util.Scanner;

public class TUI {

    public static void TUI() {

        while (true) {
            Scanner scan = new Scanner(System.in);
            MenuBesked();
            int choice = scan.nextInt();
            TheSwitch(choice);

        }

    }

    // Menu beskederne

    public static void MenuBesked() {
        System.out.println("Enter a number for which action you want to take");
        System.out.println("1. Add new user");
        System.out.println("2. Show users");
        System.out.println("3. Opdate user");
        System.out.println("4. Remove user");
    }

    //Menu switchen

    public static void TheSwitch(int choice) {

        Scanner scan = new Scanner(System.in);

        switch (choice) {
            case 1: //some thing
                System.out.println("You entered 1, to add a new user");
                System.out.println("Enter userID");
                int userID = scan.nextInt();
                scan.nextLine(); //gør så den ikke hopper over "enter user name" inputtet !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                System.out.println("Enter user name");
                String userName = scan.nextLine();
                System.out.println("Enter user ini");
                String ini = scan.nextLine();
                System.out.println("Enter user role");
                String userRole = scan.nextLine();

                break;
            case 2: //some thing
                System.out.println("du tastede 2, kode skal ind her");
                break;
            case 3: //some thing
                System.out.println("du tastede 3, kode skal ind her");
                break;
            case 4: //some thing
                System.out.println("du tastede 4, kode skal ind her");
                break;
            default:
                System.out.println("Please enter a valid input");
                System.out.println();
        }

    }

}
