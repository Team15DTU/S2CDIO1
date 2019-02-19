package data;

import data.dal.IUserDAO;
import data.dto.UserDTO;
import data.conn.Conn;

import java.sql.*;
import java.util.List;

public class DB implements IUserDAO {
	Conn c = new Conn();

	
	//region Public Methods
	
	@Override
	public UserDTO getUser(int userId) throws DALException
	{
		//TODO:
		c.Connect("");

		return null;
	}
	
	@Override
	public List<UserDTO> getUserList() throws DALException
	{
		//TODO:
		return null;
	}
	
	@Override
	public void createUser(UserDTO user) throws DALException
	{
		//TODO:
		c.statement.executeUpdate("insert into test values (" + id + "," + "'" + text + "'" + ")");
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
