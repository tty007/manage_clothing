import java.io.*;
import java.sql.*;
import java.util.*;

public class loginbase {
    public static void main(String[] args) {
        //to judge loggedin
        boolean loggedin = false;
        //save login info
        String user_name_info = "";

        System.out.print("Are you a member?[y/n] : ");
        Scanner scan = new Scanner(System.in);
        String member = scan.next();
        if (!(member.equals("y") || member.equals("n"))) {
            System.out.println("Not a correct command. Please restart this program.");
            System.exit(0);
        }
        if (member.equals("y")) {
            System.out.print("please input your name :");
            String user_name = scan.next();
            user_name_info = user_name;
            System.out.print("please input your password :");
            String user_password = scan.next();

            logincommand log = new logincommand();

            if (log.sqlcommand(user_name, user_password) == true) {
            //if logged in
            loggedin = true;
            System.out.println("logged in!");
            } else {
            //if not logged in
            System.out.println("not a member.");
            signup s = new signup();
            s.signup_notice();
            }
        } else if (member.equals("n")) {
            signup no = new signup();
            no.signup_notice();
        }
        //end of login and signup function

        if (loggedin == true) {
            System_mode mode = new System_mode(user_name_info);
            mode.show_mode();
        }

    }
}