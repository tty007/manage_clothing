import java.sql.*;
import java.util.*;
import java.lang.*;

public class Procedure_of_Returningcommand {
    //save info
    String user_name;
    String cloth_name;
    boolean exists = false;
    String borrow_cloth_name;

    public Procedure_of_Returningcommand(String cloth_name, String user_name) {
        this.cloth_name = cloth_name;
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

            String sql2 = String.format("select c.id from Clothing c where c.name = '%s'",cloth_name);
            ResultSet rs2 = statement.executeQuery(sql2);
            int cloth_id = rs2.getInt("id");

            
            String sql4 = String.format("select a.lending_time from Clothing c, active_lending a where a.clothing_id = %d", cloth_id);
            ResultSet rs4 = statement.executeQuery(sql4);
            String lend_time = rs4.getString("lending_time");

            String sql5 = String.format("select c.time from Clothing c where c.id = %d", cloth_id);
            ResultSet rs5 = statement.executeQuery(sql5);
            String rental_period = rs5.getString("time");

           
            System.out.println("\n>>Your lending time is " + lend_time + ".<<");
            System.out.println("If the period is over " + rental_period + ", a late fee is required.\nNeed to pay 500yen.");
            System.out.print("Confirm message above. Something input. : ");
            Scanner scan = new Scanner(System.in);
            String command = scan.next();

            String sql3 = String.format("select distinct(c.name) from Clothing c, active_lending a, User u where a.user_id = %d and a.clothing_id = c.id", user_id);
            ResultSet rs3 = statement.executeQuery(sql3);

            while (rs3.next()) {
                String compare = rs3.getString("name");
                if (cloth_name.equals(compare)) {
                    
                    System.out.println("\n>>Success return!<<\n");
                    exists = true;
                }
            }
            if (exists) {
                String sql = String.format("delete from active_lending where user_id = %d and clothing_id = %d", user_id, cloth_id);
                statement.executeQuery(sql);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            // e.printStackTrace();
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