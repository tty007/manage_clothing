import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;

public class System_mode {
    //save login info
    String user_name;
    
    public System_mode(String user_name) {
        this.user_name = user_name;
    }

    public void show_mode() {
        String welcome_message = String.format("Welcome to this system, '%s'. Select mode.\n-------------------------------", user_name);
        System.out.println(welcome_message);
        System.out.println("mode                    | [input]\n-------------------------------");
        System.out.println("Search Cloths           | [s]\nList of borrowed Cloths | [l]\nProcedure of lending    | [p]\nProceed of Returning    | [r]\nexit                    | [e]");
        System.out.println("-------------------------------");
        System.out.print(": ");
        Scanner scan = new Scanner(System.in);
        String input_mode = scan.next();
        //incorrect command case
        if (!(input_mode.equals("s") || input_mode.equals("p") || input_mode.equals("l") || input_mode.equals("r") || input_mode.equals("e"))) {
            System.out.println(">>please select correct mode.<<");
            System_mode reboot = new System_mode(user_name);
            reboot.show_mode();
        }
        //exit command
        if (input_mode.equals("e")){
            String exit_message = String.format("Thanks. See you %s.", user_name);
            System.out.println(exit_message);
            System.exit(0);
        }

        //Switch mode select
        switch (input_mode) {
            // Search Cloths
            case "s":
                System.out.println("----->>Cloths List<<-----");
                Search_cloths();
                System.out.println("----->>End of this List<<-----");
                // System.out.println("\nreturn select mode      | [r]\nshow what you can borrow | [s]");
                // System.out.print(": ");
                Search_cloths_input();
                break;
            // List of borrowed Cloths
            case "l":
                System.out.println("----->>This is your borrowing List!<<-----");
                Show_List_of_borrowed_Cloths();
                System.out.println("----------->>End of This List<<-----------");
                System.out.println("\nreturn select mode  | [r]\nexit                | [e]");
                System.out.print(": ");
                String show_List_of_borrowed_Cloths = scan.next();
                if (show_List_of_borrowed_Cloths.equals("r")) {
                    show_mode();
                } else if (show_List_of_borrowed_Cloths.equals("e")) {
                    System.out.println("Thanks for using. See you.");
                    System.exit(0);
                } else {
                    System.out.println("Please input correct mode.");
                    Search_cloths();
                }
                break;
            // procedure of lending
            case "p":
                System.out.println("Cloths List which you can borrow now.");
                Show_enable_to_borrow();
                break;
            // Proceed of Returning
            case "r":
                System.out.println("-------------------------------");
                Procedure_of_Returning();
                break;
            default:
                System.out.println("unknown-error occured. please restart this program.");
                System.exit(0);
        }
    //end of show_mode method.
    }

    public void Search_cloths() {
        Search_clothscommand searchcommand = new Search_clothscommand();
        searchcommand.sqlcommand();
    }

    public void Search_cloths_input() {
        System.out.println("\nreturn select mode       | [r]\nshow what you can borrow | [s]");
        System.out.print(": ");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        if (!(input.equals("r") || input.equals("s"))) {
            Search_cloths_input();
        }
        if (input.equals("r")) {
            show_mode();
        } else if (input.equals("s")) {
            Show_enable_to_borrow();
        }
    }

    public void Show_List_of_borrowed_Cloths() {
        List_of_borrowed_Clothscommand l = new List_of_borrowed_Clothscommand(user_name);
        l.sqlcommand();
    }

    public void Show_enable_to_borrow() {
        enable_to_borrowcommand e = new enable_to_borrowcommand();
        System.out.println("-------------------------------------");
        e.sqlcommand();
        System.out.println("--------->>End of this List<<--------");
        System.out.println("\nreturn select mode                | [r]\nProceed to the lending procedures | [p]\nexit                              | [e]");
        System.out.print(": ");
        Scanner scan = new Scanner(System.in);
        String p_message = scan.next();
        if (p_message.equals("r")) {
            show_mode();
        } else if (p_message.equals("e")) {
            System.out.println("Thanks for using. See you.");
            System.exit(0);
        } else if (p_message.equals("p")) {
            Procedure_of_lending();
        } else {
            System.out.println("Please input correct mode.");
            Show_enable_to_borrow();
        }
    }

    public void Procedure_of_lending() {
        System.out.print("input Cloth name you wanna borrow. : ");
        Scanner scan = new Scanner(System.in);
        String cloth_name = scan.next();
        // System.out.print("input today's date [yyyy/mm/dd] :");
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(cal.getTime());
        // if (!(date.matches("^[0-9]{4}/[0-9]{2}/[0-9]{2}$"))) {
        //     System.out.println("please input correct date");
        //     Procedure_of_lending();
        // }

        procedure_of_lendingcommand p = new procedure_of_lendingcommand(user_name, cloth_name, date);
        p.sqlcommand();
    }

    public void Procedure_of_Returning() {
        System.out.println("Please select command");
        System.out.println("-------------------------------");
        System.out.println("Retrun my cloth    | [r]\nView my List       | [v]\nReturn select mode | [s]\nexit               | [e]");
        System.out.print(": ");
        Scanner scan = new Scanner(System.in);
        String input_retrun = scan.next();
        if (input_retrun.equals("r")) {
            System.out.println("----->>This is your borrowing List!<<-----");
            Show_List_of_borrowed_Cloths();
            System.out.println("----------->>End of This List<<-----------");
            System.out.print("input Cloth name you wanna retrun : ");
            String return_cloth = scan.next();
            Procedure_of_Returningcommand r = new Procedure_of_Returningcommand(return_cloth, user_name);
            r.sqlcommand();
            Procedure_of_Returning();
        } else if (input_retrun.equals("v")) {
            System.out.println("----->>This is your borrowing List!<<-----");
            Show_List_of_borrowed_Cloths();
            System.out.println("----------->>End of This List<<-----------");
            Procedure_of_Returning();
        } else if (input_retrun.equals("s")) {
            show_mode();
        } else if (input_retrun.equals("e")) {
            System.out.println("Thanks for using. See you.");
            System.exit(0);
        } else {
            System.out.println("Please input correct mode.");
            Procedure_of_Returning();
        }

    }

}