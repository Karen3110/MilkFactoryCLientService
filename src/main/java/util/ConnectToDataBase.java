package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectToDataBase {


    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory", "root", "1234567890");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }
}
