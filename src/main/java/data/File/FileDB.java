package data.File;

import data.dto.UserDTO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Rasmus Sander Larsen
 */
public class FileDB {

    /*
    -------------------------- Fields --------------------------
     */

    private String filePath;
    private HashMap<Integer, UserDTO> fileHashMap;

    /*
    ----------------------- Constructor -------------------------
     */

    public FileDB (String fileName) {

        fileHashMap = new HashMap<>();

        setupHashMap(fileName, fileHashMap);
    }

    /*
    ------------------------ Properties -------------------------
     */

    // region Properties

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public HashMap<Integer, UserDTO> getFileHashMap() {
        return fileHashMap;
    }

    public void setFileHashMap(HashMap<Integer, UserDTO> fileHashMap) {
        this.fileHashMap = fileHashMap;
    }


    // endregion

    /*
    ---------------------- Public Methods -----------------------
     */
    
    

    /*
    ---------------------- Support Methods ----------------------
     */

    private void setupHashMap (String fileName, HashMap<Integer, UserDTO> fileHashMap) {
        try {
            filePath = getClass().getClassLoader().getResource(fileName).getPath().replace("%20", " ");

            // Reads file into HashMap.
            readFileIntoHashMap(filePath, fileHashMap);
        } catch (NullPointerException e) {

            System.out.println("Der blev ikke fundet nogen fil i klassen \"FileDB.java\" metoden setupHashmap()");
           }
    }

    private void readFileIntoHashMap(String filePath, HashMap<Integer, UserDTO> hashMap) {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] userInfoArray = line.split(";");

                infoArrayIntoHashMap(hashMap,userInfoArray);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void infoArrayIntoHashMap (HashMap<Integer,UserDTO> hashMap, String[] stringInfoArray) {

        // Gets UserID.
        String tempUserID = stringInfoArray[0];
        int tempUserID_int = Integer.parseInt(tempUserID);

        // Loads user Roles
        List<String> roles = new ArrayList<>();
        String[] roleArray = stringInfoArray[5].split(":");

        for (String role : roleArray){
            roles.add(role);
        }

        //Creates new user from the collected data.
        UserDTO tempUser = new UserDTO(tempUserID_int,stringInfoArray[1],stringInfoArray[2],
                    stringInfoArray[3], stringInfoArray[5]);

        // Adds User object to HashMap
        hashMap.put(Integer.parseInt(stringInfoArray[0]), tempUser);

    }

}
