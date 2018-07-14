import java.sql.*;

public class signupcommand {
    
    public static void sqlcommand (String name, String email, String pass) {
    
        Connection connection = null;
        Statement statement = null;
        try {
            //confirm JDBC
            Class.forName("org.sqlite.JDBC");

            //connection DB
            connection = DriverManager.getConnection("jdbc:sqlite:java.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);

            //publish query
            String sql = String.format("insert into User (name, email, password) values ('%s', '%s', '%s')", name, email, pass);
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close statement and connection.
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}