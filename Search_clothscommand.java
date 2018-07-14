import java.sql.*;

public class Search_clothscommand {
    
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
            String sql = "select * from Clothing";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.print("name : " + rs.getString("name"));
                System.out.print(" / price(yen) : " + rs.getInt("price"));
                System.out.println(" / rental period : " + rs.getString("time"));
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