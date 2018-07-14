import java.io.*;
import java.sql.*;
import java.util.*;

public class signup {
    public void signup_notice() {
        System.out.println("Signup? [y/n] :");
        Scanner scan = new Scanner(System.in);
        String signup = scan.next();
        if (!(signup.equals("y") || signup.equals("n"))) {
            System.out.println("Not a correct command. Please restart this program.");
            System.exit(0);
        }
        if (signup.equals("y")) {
            System.out.println("please input your name :");
            String user_name = scan.next();
            System.out.println("please input your email :");
            String user_email = scan.next();            
            System.out.println("please input your password :");
            String user_password = scan.next();

            signupcommand sign = new signupcommand();
            sign.sqlcommand(user_name, user_email, user_password);
            System.out.println("Success create user! Please restart this program and login.");

        } else if (signup.equals("n")) {
            System.out.println("Good bye.");
            System.exit(0);
        }
    }
}