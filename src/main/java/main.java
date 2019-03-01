import TUI.TUI;
import data.Database.DBController;

public class main {
    public static void main(String[] args)  {

        DBController dbController = new DBController();
        dbController.startUpAndRunDBSelector();

        TUI textUserInterface = new TUI(dbController.getDB());
        textUserInterface.TUI();

    }
}
