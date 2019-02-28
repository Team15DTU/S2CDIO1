package data.File;

import data.dao.IUserDAO;
import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rasmus Sander Larsen
 */
public class FileDBController implements IUserDAO {

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

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    // endregion
    
    /*
    ---------------------- Public Methods -----------------------
     */

    /**
     * Adds a UserDTO to the HashMap in FileDB.java on a keyvalue matching the UserDTO.UserID.
     * @param userDTO the User that is added.
     */

    public void createUser(UserDTO userDTO) {

        fileDB.getFileHashMap().put(userDTO.getUserId(),userDTO);

    }

    public UserDTO getUser(int userID) {

        return fileDB.getFileHashMap().get(userID);

    }

    public void updateUser(UserDTO user) {

        // Removes the User in the Hashmap with a UserID matching the inputted Users UserID.
        fileDB.getFileHashMap().remove(user.getUserId());

        // Adds the inputted UserDTO object into the HashMap with UserId as key.
        fileDB.getFileHashMap().put(user.getUserId(), user);

    }

    public void deleteUser(int userID) {

        fileDB.getFileHashMap().remove(userID);

    }

    public List<UserDTO> getUserList () {
        List<UserDTO> userList = new ArrayList<>();

        for (int userID : fileDB.getFileHashMap().keySet()) {

            userList.add(fileDB.getFileHashMap().get(userID));
        }

        return userList;
    }

    public void shutdown () {

        writer.writeToFile(fileDB.getFileHashMap());
        writer.setFilePath(writer.getFilePath().replace( "/src/main/resources/","/target/classes/"));
        writer.writeToFile(fileDB.getFileHashMap());

        System.out.println("\n... All UserDTO's from the HashMap has been saved to the CSV-file.");

    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
