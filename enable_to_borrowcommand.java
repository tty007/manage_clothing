import java.sql.*;

public class enable_to_borrowcommand {
    //save user info
    // String user_name;

    // public enable_to_borrowcommand(String user_name) {
    //     this.user_name = user_name;
    // }
    
    public void sqlcommand () {
    
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
            String sql = "select c.name from Clothing c left outer join active_lending a on (c.id = a.clothing_id) where a.clothing_id is null";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }

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