package data.File;

import data.dto.UserDTO;

/**
 * @author Rasmus Sander Larsen
 */
public class FileDBController {

    /*
    -------------------------- Fields --------------------------
     */
    
    private FileDB fileDB;
    private Writer writer;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public FileDBController () {

        fileDB = new FileDB("fileDB.csv");
        writer = new Writer("fileDB.csv");
    }
    
    /*
    ------------------------ Properties -------------------------
     */

    // region Properties

    public FileDB getFileDB() {
        return fileDB;
    }

    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }


    // endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * Adds a UserDTO to the HashMap in FileDB.java on a keyvalue matching the UserDTO.UserID.
     * @param userDTO the User that is added.
     */
    public void addUserToFileDBHashMap (UserDTO userDTO) {

        fileDB.getFileHashMap().put(userDTO.getUserId(),userDTO);

    }

    public UserDTO showUserFromFileDBHashMap (int userID) {

        return fileDB.getFileHashMap().get(userID);

    }

    public void updateUserInFileDBHashMap () {

    }

    public void removeUserFromFileDBHashMap (int userID) {

        fileDB.getFileHashMap().remove(userID);

    }

    public void shutdownAndSaveToFile () {
        writer.writeToFile(fileDB.getFileHashMap());
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
