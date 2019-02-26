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

    private String filePath;

    /*
    ----------------------- Constructor -------------------------
     */
    
    public Writer (String fileName) {

        filePath = setupFilePath(fileName);

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * Writes the info from user and builds one big string, with keys splitted with ;.
     * @param hashMap
     */
    
    public void writeToFile (HashMap<Integer, UserDTO> hashMap) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath) )) {

            for (int key : hashMap.keySet()) {
                StringBuilder tempUserInfo = new StringBuilder();
                StringBuilder roleBuilder = new StringBuilder();

                tempUserInfo.append(hashMap.get(key).getUserId() + ";");
                tempUserInfo.append(hashMap.get(key).getUserName() + ";");
                tempUserInfo.append(hashMap.get(key).getIni() + ";");
                tempUserInfo.append(hashMap.get(key).getCpr() + ";");
                tempUserInfo.append(hashMap.get(key).getPassword() + ";");
                for (int j = 0; j < hashMap.get(key).getRoles().size(); j++){
                    if (j!=hashMap.get(key).getRoles().size()-1) {
                        roleBuilder.append(hashMap.get(key).getRoles().get(j) + ":");
                    } else {
                        roleBuilder.append(hashMap.get(key).getRoles().get(j));
                    }
                }
                tempUserInfo.append(roleBuilder.toString() + "\n");

                bufferedWriter.write(tempUserInfo.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private String setupFilePath (String fileName) {

        return getClass().getClassLoader().getResource(fileName).getPath().replace("%20", " ")
                .replace("/target/classes/", "/src/main/resources/");
    }
}
