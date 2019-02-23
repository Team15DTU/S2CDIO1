package data;

import data.dal.IUserDAO;
import data.dto.UserDTO;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class DB implements IUserDAO {

	private Connection createConnection() throws DALException {
		try {
			return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185100?",
                    "s185100","MTY7Fw0BTWcnwMWmrHekT");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	//region Public Methods
	@Override
	public UserDTO getUser(int userId) throws DALException
	{
	    Connection c = createConnection();
        UserDTO user = new UserDTO();

        try {
            Statement statement = c.createStatement();

            //statement.executeUpdate("SELECT * FROM CDIO1 WHERE userID=1");
            ResultSet rs = statement.executeQuery("SELECT * FROM CDIO1 WHERE userID="+userId);

            while (rs.next()){
				int UserId = rs.getInt("userID");
				String userName = rs.getString("userName");
				String ini = rs.getString("ini");
				String cpr = rs.getString("cpr");
				String password = rs.getString("password");
				String roles = rs.getString("roles");

				System.out.println(UserId + "\t" + userName +
						"\t" + ini + "\t" + cpr +
						"\t\t" + password + "\t" +roles);
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
	public List<UserDTO> getUserList() throws DALException {

		Connection c = createConnection();
		try {
			Statement statement = c.createStatement();
			//statement.executeUpdate("SELECT * FROM CDIO1 WHERE userID=1");
			ResultSet rs = statement.executeQuery("SELECT * FROM CDIO1");

			while (rs.next()){
				int UserId = rs.getInt("userID");
				String userName = rs.getString("userName");
				String ini = rs.getString("ini");
				String cpr = rs.getString("cpr");
				String password = rs.getString("password");
				String roles = rs.getString("roles");

				System.out.println("UserID: "+UserId + " UserName: " + userName +
						" Ini: " + ini + " Cpr: " + cpr +
						" Password: " + password + " Rolle: " +roles);
			}
			statement.close();
			c.close();
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}

		return null;
	}
	
	@Override
	public void createUser(UserDTO user) throws DALException {

        Connection c = createConnection();
        Scanner s = new Scanner(System.in);

        try {
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
            c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }

	}
	
	@Override
	public void updateUser(UserDTO user) throws DALException {
        Connection c = createConnection();
        Scanner s = new Scanner(System.in);

        try {
            Statement statement = c.createStatement();

            System.out.println("Indtast id for bruger hvis oplysinger du vil opdatere.");
            int UserID = s.nextInt();

            System.out.println("Vælg hvilken type information du vil opdatere.");
            System.out.println("Tast 1 for at opdatere brugernavn og initialer, 2 for at opdatere password og 3 for at opdatere rolle.");

            int choice = s.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Indtast nyt brugernavn: ");
                    String newUserName = s.next();
                    System.out.println("Indtast nye initialer: ");
                    String newIni = s.next();
                    statement.executeUpdate("UPDATE CDIO1 SET userName='"+ newUserName +"', ini='"+ newIni+"' WHERE userID="+UserID);
                    break;

                case 2:
                    System.out.println("Indtast nyt password: ");
                    String newPassword = s.next();
                    statement.executeUpdate("UPDATE CDIO1 SET password='"+ newPassword +"' WHERE userID="+UserID);
                    break;

                case 3:
                    System.out.println("Indtast nyt password: ");
                    String newRole = s.next();
                    statement.executeUpdate("UPDATE CDIO1 SET roles='"+ newRole +"' WHERE userID="+UserID);
                    break;
            }

            statement.close();
            c.close();
        }
        catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
	}
	
	@Override
	public void deleteUser(int userId) throws DALException {
        Connection c = createConnection();
        Scanner s = new Scanner(System.in);

        try {
            Statement statement = c.createStatement();

            System.out.println("Indtast id for den bruger du gerne vil slette");
            int UserID = s.nextInt();

            statement.executeUpdate("DELETE FROM CDIO1 WHERE userID="+UserID);
        }
        catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
	}
	
	
	//endregion
}
