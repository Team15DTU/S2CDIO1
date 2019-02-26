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
    ---------------------- Support Methods ----------------------
     */

    /**
     * Sets the HashMap up
     * @param fileName
     * @param fileHashMap
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

    /**
     * Reads file into HashMap and splitting them with ;.
     * @param filePath
     * @param hashMap
     */

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

    /**
     * The infoArray gets converted into a HashMap, so that we can use it in .csv files.
     * @param hashMap
     * @param stringInfoArray
     */

    private void infoArrayIntoHashMap (HashMap<Integer,UserDTO> hashMap, String[] stringInfoArray) {

        UserDTO tempUser = new UserDTO();

        // Sets values for tempUser.

        tempUser.setUserId(Integer.parseInt(stringInfoArray[0]));
        tempUser.setUserName(stringInfoArray[1]);
        tempUser.setIni(stringInfoArray[2]);
        tempUser.setCpr(stringInfoArray[3]);
        tempUser.setRoles(makeListOfStringsFromString(stringInfoArray[4]));
        tempUser.setPassword(stringInfoArray[5]);

        // Adds tempUser object to HashMap
        hashMap.put(tempUser.getUserId(), tempUser);

    }

    private List<String> makeListOfStringsFromString(String listAsString) {

        List<String> list = new ArrayList<>();
        String[] roleArray = listAsString.split(":");

        for (String role : roleArray){
            list.add(role);
        }
        return list;
    }

}
