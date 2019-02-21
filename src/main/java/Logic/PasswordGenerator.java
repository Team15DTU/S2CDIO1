package Logic;

import java.util.Random;

public class PasswordGenerator {

    /*
    -------------------------- Fields --------------------------
     */
    private String password = "S";
    int bigLetter = 0;
    int smallLetters = 0;
    int numbers = 0;

    
    /*
    ----------------------- Constructor -------------------------
     */



    /*
    ------------------------ Properties -------------------------
     */


    // Regin Properties
    public String randomPassword() {
        Random generator = new Random();

        for (int i = 0; i<10; i++) {
            int nr = generator.nextInt(60) + 1;
            password += randomSwitch(nr);

            //tæller hvor mange tal, små / store bogstaver som er i passwordet
            if (nr <= 25) {smallLetters++;}
            else if (nr > 25 && nr <= 50) {bigLetter++;}
            else if (nr > 50) {numbers++;}
        }
        if (smallLetters == 0) {password += randomSwitch(generator.nextInt(25)+1);}
        else if (bigLetter == 0) {password += randomSwitch(generator.nextInt(25)+26);}
        else if (numbers == 0) {password += randomSwitch(generator.nextInt(10)+51);}


        return password;

    }


    // endregion

    public String randomSwitch(int i){
        String c;
        switch (i) {
            case 1:
                c = "a";
                break;
            case 2:
                c = "b";
                break;
            case 3:
                c = "c";
                break;
            case 4:
                c = "d";
                break;
            case 5:
                c = "e";
                break;
            case 6:
                c = "f";
                break;
            case 7:
                c = "g";
                break;
            case 8:
                c = "h";
                break;
            case 9:
                c = "i";
                break;
            case 10:
                c = "j";
                break;
            case 11:
                c = "k";
                break;
            case 12:
                c = "l";
                break;
            case 13:
                c = "m";
                break;
            case 14:
                c = "n";
                break;
            case 15:
                c = "o";
                break;
            case 16:
                c = "p";
                break;
            case 17:
                c = "q";
                break;
            case 18:
                c = "r";
                break;
            case 19:
                c = "s";
                break;
            case 20:
                c = "t";
                break;
            case 21:
                c = "u";
                break;
            case 22:
                c = "v";
                break;
            case 23:
                c = "x";
                break;
            case 24:
                c = "y";
                break;
            case 25:
                c = "z";
                break;
            case 26:
                c = "A";
                break;
            case 27:
                c = "B";
                break;
            case 28:
                c = "C";
                break;
            case 29:
                c = "D";
                break;
            case 30:
                c = "E";
                break;
            case 31:
                c = "F";
                break;
            case 32:
                c = "G";
                break;
            case 33:
                c = "H";
                break;
            case 34:
                c = "I";
                break;
            case 35:
                c = "J";
                break;
            case 36:
                c = "K";
                break;
            case 37:
                c = "L";
                break;
            case 38:
                c = "M";
                break;
            case 39:
                c = "N";
                break;
            case 40:
                c = "O";
                break;
            case 41:
                c = "P";
                break;
            case 42:
                c = "Q";
                break;
            case 43:
                c = "R";
                break;
            case 44:
                c = "S";
                break;
            case 45:
                c = "T";
                break;
            case 46:
                c = "U";
                break;
            case 47:
                c = "V";
                break;
            case 48:
                c = "X";
                break;
            case 49:
                c = "Y";
                break;
            case 50:
                c = "Z";
                break;
            case 51:
                c = "1";
                break;
            case 52:
                c = "2";
                break;
            case 53:
                c = "3";
                break;
            case 54:
                c = "4";
                break;
            case 55:
                c = "5";
                break;
            case 56:
                c = "6";
                break;
            case 57:
                c = "7";
                break;
            case 58:
                c = "8";
                break;
            case 59:
                c = "9";
                break;
            case 60:
                c = "0";
                break;
            default:
                c = "NOTREAL";
                break;
        }
        return c;
    }

    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}