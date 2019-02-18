package data.File;

/**
 * @author Rasmus Sander Larsen
 */
public class FileDBController {

    /*
    -------------------------- Fields --------------------------
     */
    
    private FileDB fileDB;
    
    /*
    ----------------------- Constructor -------------------------
     */
    
    public FileDBController () {
        fileDB = new FileDB("fileDB.csv");
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
    
    
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
