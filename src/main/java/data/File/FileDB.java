package data.File;

import data.dto.UserDTO;

import java.io.*;
import java.util.HashMap;

/**
 * @author Rasmus Sander Larsen
 */
public class FileDB {

    /*
    -------------------------- Fields --------------------------
     */

    private String filePath;
    private HashMap<String, UserDTO> fileHashMap;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public FileDB (String fileName) {

        fileHashMap = new HashMap<>();

    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // <editor-folder desc="Properties"


    // </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void setupHashMap (String fileName) {
        try {
            filePath = getClass().getClassLoader().getResource(fileName).getPath().replace("%20", " ");

            // Reads file into HashMap.
            readFileIntoHashMap();
        } catch (FileNotFoundException e) {

           }
    }

    private void readFileIntoHashMap() {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] tempKeyAndValue = line.split(";");

                infoArrayIntoHashMap(fileHashMap,tempKeyAndValue);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private void infoArrayIntoHashMap (HashMap<String,UserDTO> hashMap, String[] stringInfoArray) {
        UserDTO tempUser = new UserDTO(Integer.parseInt(stringInfoArray[0]),stringInfoArray[1],stringInfoArray[2],
                    stringInfoArray[3], stringInfoArray[4], stringInfoArray[5]);

        hashMap.put(stringInfoArray[1], tempUser);

    }

    public void
}
