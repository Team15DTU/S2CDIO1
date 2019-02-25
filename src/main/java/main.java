import TUI.TUI;
import data.Database.DBController;

/**
 * @author Rasmus Sander Larsen
 */

public class main {

    public static void main(String[] args) {

        DBController dbController = new DBController();
        dbController.startUpAndDBSelector();

        TUI textUserInterface = new TUI(dbController.getDB());
        textUserInterface.TUI();

    }

}
