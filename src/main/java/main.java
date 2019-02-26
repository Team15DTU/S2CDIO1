import TUI.TUI;
import data.Database.DBController;
import data.dao.IUserDAO;

public class main {
    public static void main(String[] args) throws IUserDAO.DALException {

        DBController dbController = new DBController();
        dbController.startUpAndRunDBSelector();

        TUI textUserInterface = new TUI(dbController.getDB());
        textUserInterface.TUI();

    }
}
