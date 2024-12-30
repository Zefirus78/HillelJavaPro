package hw_29;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

    public static void initialize() {

        try(Connection connection = DriverManager.getConnection(DBProperties.URL, DBProperties.USER, DBProperties.PASSWORD);
        Statement statement = connection.createStatement()) {

            Class.forName("org.postgresql.Driver");

            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(100),"
                    + "age INTEGER,"
                    + "position VARCHAR(50),"
                    + "salary FLOAT);";
            statement.execute(createTableSQL);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(DBProperties.URL, DBProperties.USER, DBProperties.PASSWORD);
    }
}
