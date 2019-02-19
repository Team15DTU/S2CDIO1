package data.conn;

import java.sql.*;
import java.util.Scanner;

public class Conn {
    public void Connect(String stmt, String stmt2){
        String valg;
        Scanner scanner = new Scanner(System.in);
        boolean forkert;

        //loadDriver(); //Obsolete - only needed in rare cases.
        //try with resources (Java 7) - automatically calls connection.close() on end of try-catch block
        //Ensures that connections aren't left hanging
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185114?",
                "s185114","LJYIY16CjeCktHQSlYnXS")){
            Statement statement = connection.createStatement();
            //statement.executeQuery("use s185114");
            do {
                ResultSet resultSet = statement.executeQuery(stmt);
                System.out.println("Got resultset from database:");
                while (resultSet.next()){
                    System.out.println(resultSet.getString(1) + ": " + resultSet.getString(2));
                }
                System.out.println("Press 1 for at tilføje, 2 for at slette & 3 for at lukke");

                do {
                    valg = scanner.next();
                    forkert = false;
                    if (valg.equals("1")) {
                        System.out.println("insert");
                        int id = scanner.nextInt();
                        String text = scanner.next();
                        statement.executeUpdate("insert into test values (" + id + "," + "'" + text + "'" + ")");
                    } else if (valg.equals("2")) {
                        System.out.println("delete");
                        int id = scanner.nextInt();
                        statement.executeUpdate("DELETE FROM test where id=" + id);
                    }  else if (!valg.equals("3")){
                        System.out.println("indtast ny værdi");
                        forkert=true;
                    }
                }while (forkert);

            }while (!valg.equals("3"));
            System.out.println("Hav en god dag.");
            scanner.close();
            statement.close();
        } catch (SQLException e) {
            //Remember to handle Exceptions gracefully! Connection might be Lost....
            e.printStackTrace();
            System.out.println("hej jeg er en fejl");
        }

    }
}
