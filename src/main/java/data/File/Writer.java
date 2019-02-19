package data.File;

import data.dto.UserDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 */
public class Writer {

    /*
    -------------------------- Fields --------------------------
     */

    private String fileName;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public Writer (String fileName) {
        this.fileName = fileName;
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    public void writeToFile (HashMap<Integer, UserDTO> hashMap) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName) )) {

            for (int i = 0; i < hashMap.size(); i++) {
                StringBuilder tempUserInfo = new StringBuilder();
                StringBuilder roleBuilder = new StringBuilder();

                tempUserInfo.append(hashMap.get(i).getUserId() + ";");
                tempUserInfo.append(hashMap.get(i).getUserName() + ";");
                tempUserInfo.append(hashMap.get(i).getIni() + ";");
                tempUserInfo.append(hashMap.get(i).getCpr() + ";");
                tempUserInfo.append(hashMap.get(i).getPassword() + ";");
                for (int j = 0; j < hashMap.get(i).getRoles().size(); j++){
                    if (j!=hashMap.get(i).getRoles().size()) {
                        roleBuilder.append(hashMap.get(i).getRoles().get(j) + ",");
                    } else {
                        roleBuilder.append(hashMap.get(i).getRoles().get(j));
                    }
                }
                tempUserInfo.append(roleBuilder.toString() + "\n");

                bufferedWriter.write(tempUserInfo.toString());
            }

            System.out.println("Så er alle UserDTO'er fra HashMap'et skrevet til filen....");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */
}
