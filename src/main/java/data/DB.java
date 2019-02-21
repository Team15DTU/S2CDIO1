package data;

import data.dal.IUserDAO;
import data.dto.UserDTO;
import data.conn.Conn;

import java.sql.*;
import java.util.List;

public class DB implements IUserDAO {

	private Connection createConnection() throws DALException {
		try {
			return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185100?",
                    "s185100","MTY7Fw0BTWcnwMWmrHekT");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

    public void Test() throws DALException
    {
        Connection c = createConnection();
        //TODO:
       // UserDTO user = new UserDTO();

        try {
            Statement statement = c.createStatement();

            //statement.executeUpdate("SELECT * FROM CDIO1 WHERE userID=1");
            ResultSet rs = statement.executeQuery("SELECT * FROM CDIO1 WHERE userID=1");

            while (rs.next()){
				//FIXME skal implementeres ordentligt i klassen UserDTO
            	int userId = rs.getInt("userID");
				String userName = rs.getString("userName");
				String ini = rs.getString("ini");
				String cpr = rs.getString("cpr");
				String password = rs.getString("password");
				String roles = rs.getString("roles");

				System.out.println(userId + "\t" + userName +
                        "\t" + ini + "\t" + cpr +
                        "\t\t" + password + "\t" +roles);
            }


            //statement.close();
            //c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


	//region Public Methods
	@Override
	public UserDTO getUser(int userId) throws DALException
	{
	    Connection c = createConnection();
		//TODO:
        UserDTO user = new UserDTO();

        try {
            Statement statement = c.createStatement();

            //statement.executeUpdate("SELECT * FROM CDIO1 WHERE userID=1");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CDIO1 WHERE userID=1");

            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
            }


            statement.close();
            c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
		//c.Connect("SELECT * FROM CDIO1 WHERE userId="+userId,"");
		return user;

	}
	
	@Override
	public List<UserDTO> getUserList() {
		//TODO:
        //c.Connect("SELECT userID, userName, ini, cpr, roles FROM CDIO1", "");
		return null;
	}
	
	@Override
	public void createUser(UserDTO user) {
		//TODO:

        //c.Connect("", "");
	}
	
	@Override
	public void updateUser(UserDTO user) {
		//TODO:
	}
	
	@Override
	public void deleteUser(int userId) {
		//TODO:
	}
	
	
	//endregion
}
