package dal;

import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    List<UserDTO> userList = new ArrayList<UserDTO>();

    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        System.out.println(userList.size());
        return userList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        userList.add(user);
        System.out.println("User is added");
        System.out.println(userList.get(0).getUserName());
        System.out.println(userList.size());
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
