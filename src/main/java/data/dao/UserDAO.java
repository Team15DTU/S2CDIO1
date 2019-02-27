package data.dao;

import data.dto.UserDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserDAO implements IUserDAO {

    // ----------------- FIELDS ---------------

    private List<UserDTO> userDTOList;

    // ------------- CONSTRUCTOR -----------------

    public UserDAO () {
        userDTOList = new ArrayList<>();
    }

    // -------------- Getters & Setters ------------------

    // region Getters and Setters

    public List<UserDTO> getUserDTOList() {
        return userDTOList;
    }

    public void setUserDTOList(List<UserDTO> userDTOList) {
        this.userDTOList = userDTOList;
    }

    // endregion

    // ----------------- PUBLIC METHODS -------------------------
    @Override
    public UserDTO getUser(int userId) throws DALException {
        
        for (UserDTO user : userDTOList)
        {
            if (user.getUserId() == userId)
                return user;
        }
        
        // Throw DALException as no one is found
        throw new DALException("Wrong UserID");
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {

        try {
            return userDTOList;
        }
        catch (Exception e)
        {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

        try {
            userDTOList.add(user);
        }
        catch (Exception e)
        {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
    
        /*******************************
         * Find the user that is changed,
         * and update that element.
         *******************************/
        int userID = user.getUserId();
        for (int i=0; i < userDTOList.size(); i++)
        {
            if (userDTOList.get(i).getUserId() == userID)
            {
                // Remove and add update UserDTO object
                userDTOList.remove(i); userDTOList.add(user);
                // Sort the list
                userDTOList.sort(null);
                return;
            }
        }
        
        // Throw new DALException if no ID match
        throw new DALException("updateUser failure, Can't match ID's");
    }


    @Override
    public void deleteUser(int userId) throws DALException {

        List userList = getUserList();
        for (int i = 0; i<userList.size(); i++) {
            if (getUser(i).getUserId() == userId) {
                userDTOList.remove(i);
                return;
            }
        }
        
        // If userId not found throw DALException
        throw new DALException("UserID not found");

    }

    public void shutdown () {

    }







}
