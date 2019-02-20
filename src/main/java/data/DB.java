package data;

import data.dal.IUserDAO;
import data.dto.UserDTO;
import data.conn.Conn;

import java.sql.*;
import java.util.List;

public class DB implements IUserDAO {
	Conn c = new Conn();
	
	//region Public Methods
    //FIXME DALException virker ikke så den er udkommenteret i getUser for test testing.
	@Override
	public UserDTO getUser(int userId) //throws DALException
	{
		//TODO:
		c.Connect("SELECT * FROM CDIO1 WHERE userId="+userId,"");
		return null;

	}
	
	@Override
	public List<UserDTO> getUserList() throws DALException
	{
		//TODO:
        c.Connect("SELECT userID, userName, ini, cpr, roles FROM CDIO1", "");
		return null;
	}
	
	@Override
	public void createUser(UserDTO user) throws DALException
	{
		//TODO:

        c.Connect("", "");
	}
	
	@Override
	public void updateUser(UserDTO user) throws DALException
	{
		//TODO:
	}
	
	@Override
	public void deleteUser(int userId) throws DALException
	{
		//TODO:
	}
	
	
	//endregion
}
