import java.sql.*;

public class procedure_of_lendingcommand {
    //save info
    String user_name;
    String cloth_name;
    String date;
    boolean exists = false;
    String borrow_cloth_name;
    int uid;
    int cid;

    public procedure_of_lendingcommand(String user_name, String cloth_name, String date) {
        this.user_name = user_name;
        this.cloth_name = cloth_name;
        this.date = date;
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
            String sql = "select c.name from Clothing c left outer join active_lending a on (c.id = a.clothing_id) where a.clothing_id is null";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                if (cloth_name.equals(rs.getString("name"))) {
                    exists = true;
                    borrow_cloth_name = cloth_name;
                    String get_uid_sql = String.format("select id from User where name = '%s'", user_name);
                    ResultSet rs1 = statement.executeQuery(get_uid_sql);
                    uid = rs1.getInt("id");

                    String get_cid_sql = String.format("select id from Clothing where name = '%s'", borrow_cloth_name);
                    ResultSet rs2 = statement.executeQuery(get_cid_sql);
                    cid = rs2.getInt("id");

                    String insertsql = String.format("insert into active_lending (user_id, clothing_id, lending_time) values (%d, %d, %s)", uid, cid, date);
                    statement.executeUpdate(insertsql);
                    System.out.println("\n" + date);
                    System.out.println(">>You borrowed that!<<\n");
                    System_mode s = new System_mode(user_name);
                    s.show_mode();
                }
            }
            if (exists == false) {
                System.out.println("\n>>Your select is wrong. The cloth does not exist on List you can borrow now!\nSelect below.<<");
                System_mode s = new System_mode(user_name);
                s.Show_enable_to_borrow();
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