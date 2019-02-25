import data.DB;
import data.dal.IUserDAO;
import data.dto.UserDTO;

public class main {
    public static void main(String[] args) throws IUserDAO.DALException {
        DB db = new DB();
        //TODO db.getUser gør ikke noget den står der bare for noget test. (kan fjernes)
        //db.getUser(2);
        //db.getUserList();
        //db.createUser(db.getUser(999));
        //db.updateUser(db.getUser(999));
        //db.Test();
    }
}
