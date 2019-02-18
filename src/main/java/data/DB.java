package data;

import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.util.List;

public class DB implements IUserDAO {
	
	
	
	//region Public Methods
	
	@Override
	public UserDTO getUser(int userId) throws DALException
	{
		//FIXME:
		return null;
	}
	
	@Override
	public List<UserDTO> getUserList() throws DALException
	{
		//FIXME:
		return null;
	}
	
	@Override
	public void createUser(UserDTO user) throws DALException
	{
		//FIXME:
	}
	
	@Override
	public void updateUser(UserDTO user) throws DALException
	{
		//FIXME:
	}
	
	@Override
	public void deleteUser(int userId) throws DALException
	{
		//FIXME:
	}
	
	
	//endregion
}
