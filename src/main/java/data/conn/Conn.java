package data.conn;

import java.sql.*;
import java.util.Scanner;

public class Conn {
    public void Connect(String stmt, String stmt2){
        Scanner scanner = new Scanner(System.in);

        //loadDriver(); //Obsolete - only needed in rare cases.
        //try with resources (Java 7) - automatically calls connection.close() on end of try-catch block
        //Ensures that connections aren't left hanging
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185114?",
                "s185114","LJYIY16CjeCktHQSlYnXS")){
            Statement statement = connection.createStatement();
            //statement.executeQuery("use s185114");
           // do {

                if (!stmt2.equals("")) {
                    statement.executeUpdate(stmt2);
                }
                ResultSet resultSet = statement.executeQuery(stmt);

                while (resultSet.next()){
                    System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
                }

            scanner.close();
            statement.close();

        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
            System.out.println("hej jeg er en fejl");
        }

    }
}
