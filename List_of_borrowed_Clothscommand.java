import java.sql.*;

public class List_of_borrowed_Clothscommand {
    //save user info
    String user_name;

    public List_of_borrowed_Clothscommand(String user_name) {
        this.user_name = user_name;
    }
    
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
            String sql1 = String.format("select u.id from User u where u.name = '%s'",user_name);
            ResultSet rs1 = statement.executeQuery(sql1);
            int user_id = rs1.getInt("id");
            String sql2 = String.format("select distinct(c.name) from Clothing c, active_lending a, User u where a.user_id = %d and a.clothing_id = c.id", user_id);
            ResultSet rs2 = statement.executeQuery(sql2);
            while (rs2.next()) {
                System.out.println(rs2.getString("name"));
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