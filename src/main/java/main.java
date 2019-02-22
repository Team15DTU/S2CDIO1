import data.DB;
import data.dal.IUserDAO;

public class main {
    public static void main(String[] args) throws IUserDAO.DALException {
        DB db = new DB();
        //TODO db.getUser gør ikke noget den står der bare for noget test. (kan fjernes)
        //db.getUser(1);
        db.Test();
    }
}
