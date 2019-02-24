package data.File;

import data.dal.IUserDAO;
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

    public void shutdown () throws DALException {
        writer.writeToFile(fileDB.getFileHashMap());
        writer.setFilePath(writer.getFilePath().replace( "/src/main/resources/","/target/classes/"));
        writer.writeToFile(fileDB.getFileHashMap());
    }
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
