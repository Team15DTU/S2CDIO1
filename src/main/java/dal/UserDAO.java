package dal;

import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    ArrayList<UserDTO> userList = new ArrayList<UserDTO>();

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        userList.add(user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        UserDTO newUser;
        for (int i = 0; i>userList.size(); i++) {
            if (userList.get(i).getUserId() == user.getUserId()) {
                newUser = userList.get(i);
            }
        }
        System.out.println("Insert ");

    }


    @Override
    public void deleteUser(int userId) throws DALException {

        for (int i = 0; i<userList.size(); i++) {
            if (userList.get(i).getUserId() == userId) {
                userList.remove(i);
            }
        }

    }



}
