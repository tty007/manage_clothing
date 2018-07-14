import java.sql.*;

public class logincommand {
    
    public static boolean sqlcommand (String name, String pas) {
    
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
            String pass = String.format("select password from User where name = '%s'", name);
            ResultSet rs = statement.executeQuery(pass);

            if (pas.equals(rs.getString("password"))) {
                return true;
            }
        
            return false;

        } catch (ClassNotFoundException e) {
            // e.printStackTrace();
            return false;
        } catch (SQLException e) {
            // e.printStackTrace();
            return false;
        } finally {
            //close statement and connection.
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