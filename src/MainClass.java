import Admin.Administration;
import DB.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class MainClass {
    public static Administration adOB;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int AdminID = 1010;
        int Password = 1010;

        System.out.println("If you are an Admin, press 1. unless press 2");
        int choice = in.nextInt();

        do {
            switch(choice){
                case(1) :
                    System.out.println("Enter Your Admin ID...");
                    int getAdminId = in.nextInt();
                    System.out.println("Enter Your Admin Password...");
                    int getPassword = in.nextInt();
                    if(AdminID==getAdminId && Password==getPassword){
                        //Connection connection = DBConnection.getInstance().getConnection();
                        //System.out.println(connection);
                        Administration admin = new Administration();
                        admin.start();
                    }else{
                        System.out.println("Wrong Password...");
                    }

                case (2):
                    System.out.println("Have a nice day....");
                    choice=0;
                    break;

                default:
                    System.out.println("Wrong input, try again....");
                    choice=0;
                    break;
            }
        }while (choice!=0);

    }

}
