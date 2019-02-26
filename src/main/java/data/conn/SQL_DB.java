package data.conn;

import data.dao.IUserDAO;
import data.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SQL_DB implements IUserDAO {

	//region Public Methods
	@Override
	public UserDTO getUser(int userId) throws DALException {
        UserDTO user = new UserDTO();

        try (Connection c = createConnection();) {
            Statement statement = c.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM CDIO1 WHERE userID="+userId);

            while (rs.next()){
				user.setUserId(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setIni(rs.getString("ini"));
				user.setCpr(rs.getString("cpr"));
				user.setPassword(rs.getString("password"));
				user.setRoles(makeStringListFromString(rs.getString("roles")));
            }
            statement.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

		return user;
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {

	    List<UserDTO> userDTOList = new ArrayList<>();

		try (Connection c = createConnection();) {
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("SELECT userID FROM CDIO1");

			while (rs.next()){
			    int userID = rs.getInt("userID");
				userDTOList.add(getUser(userID));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userDTOList;
	}

	//TODO: DENNE METODE SKAL FIXES!!
	@Override
	public void createUser(UserDTO user) throws DALException {


        Scanner s = new Scanner(System.in);

        try (Connection c = createConnection()) {
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(userID) FROM CDIO1");
            while (rs.next()) {
                int MaxID = rs.getInt(1);
                System.out.println("Vælg et user id der skal være imellem "+ MaxID +" og 99");
            }
            System.out.println("Indtast bruger id:");
            int UserID = s.nextInt();
            System.out.println("Indtast brugernavn:");
            String UserName = s.next();
            System.out.println("Indtast initialer");
            String Ini = s.next();
            System.out.println("Indtast cpr nummer eks. 123456-1234");
            String Cpr = s.next();
            System.out.println("Indtast password");
            String Password = s.next();
            System.out.println("Indtast rolle");
            String Roles = s.next();

            statement.executeUpdate("INSERT INTO CDIO1 (userID, userName, ini, cpr, password, roles)" + " VALUES ("+UserID+", '"+UserName+"', '"+Ini+"', '"+Cpr+"', '"+Password+"', '"+Roles+"')");

            statement.close();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
	}
	
	@Override
	public void updateUser(UserDTO user) throws DALException {
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder.append("UPDATE CDIO1 SET ");
        queryBuilder.append("userName = \"" + user.getUserName() + "\",");
        queryBuilder.append("ini = \"" + user.getIni() + "\",");
        queryBuilder.append("cpr = \"" + user.getCpr() + "\",");
        queryBuilder.append("password = \"" + user.getPassword() + "\",");
        queryBuilder.append("roles = \"" + makeStringFromStringList(user.getRoles()) + "\"");
        queryBuilder.append("WHERE userID =" + user.getUserId());

        try (Connection c = createConnection()){

            Statement statement = c.createStatement();

            statement.executeUpdate(queryBuilder.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void deleteUser(int userId) throws DALException {

        Scanner s = new Scanner(System.in);

        try (Connection c = createConnection()) {
            Statement statement = c.createStatement();

            System.out.println("Enter the UserID for the User you wishes to delete");
            int UserID = s.nextInt();

            statement.executeUpdate("DELETE FROM CDIO1 WHERE userID="+UserID);
        }
        catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
	}

	@Override
	public void shutdown () {

    }

	//endregion

    // ----------------- SUPPORT METHODS -------------------

    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185100?",
                    "s185100","MTY7Fw0BTWcnwMWmrHekT");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    private String makeStringFromStringList (List<String> list) {
	    StringBuilder listStringBuilder = new StringBuilder();

	    for (String s : list) {
	        listStringBuilder.append(s + ":");
        }

	    return listStringBuilder.toString();
    }

    private List<String> makeStringListFromString (String listString) {
	    List<String> stringList = new ArrayList<>();
	    String[] stringArray = listString.split(":");

	    for (String s : stringArray) {
	        stringList.add(s);
        }

	    return stringList;
    }
}
