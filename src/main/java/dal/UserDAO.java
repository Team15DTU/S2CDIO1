package dal;

import data.UserList;
import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    UserList UL = new UserList();

    @Override
    public UserDTO getUser(int userId) throws DALException {
        List<UserDTO> list = UL.UserL();
        UserDTO user;

        for (int i = 0; i<list.size(); i++) {
            if (userId == list.get(i).getUserId()) {
                user = list.get(i);
                return user;
            }
        }

        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        List<UserDTO> list = UL.UserL();
        return list;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

        UL.add(user);

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {


    }


    @Override
    public void deleteUser(int userId) throws DALException {


    }







}
