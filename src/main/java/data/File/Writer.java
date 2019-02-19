package data.File;

import data.dto.UserDTO;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
        setupFilePath(fileName);


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

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath) )) {



        }
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */

    private void setupFilePath (String fileName) {
        try {
            filePath = getClass().getClassLoader().getResource(fileName).getPath().replace("%20", " ");

        } catch (NullPointerException e) {

            System.out.println("Der blev ikke fundet nogen fil i klassen \"FileDB.java\" metoden setupHashmap()");
        }
    }

}
