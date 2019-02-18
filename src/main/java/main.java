import data.File.FileDBController;

/**
 * @author Rasmus Sander Larsen
 */
public class main {

    public static void main(String[] args) {

        FileDBController fileDBController = new FileDBController();
        System.out.println(fileDBController.getFileDB().getFileHashMap().get(101010));
    
    }
}
