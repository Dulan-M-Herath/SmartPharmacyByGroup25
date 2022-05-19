package Admin;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:21 pm
 * @project name : SmartPharmacyPOS
 */

import Customer_Employee.Customer;
import Customer_Employee.Employee;
import DB.DBConnection;
import Drugs_Product.Billing;
import Drugs_Product.Drugs;
import Drugs_Product.Products;
import Drugs_Product.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Administration {

    Scanner in = new Scanner(System.in);

    Employee e1 = new Employee();
    getEmployee gE1 = new getEmployee();
    Customer c1 = new Customer("A", "B", "C", "D", 1);
    getCustomer gC1 = new getCustomer();


    private int choice;

    public void start() {
        System.out.printf("\t\t\t\t.......Welcome Admin.......\n");
        String dateAndTime = CurrentDateTime();
        System.out.println(dateAndTime);
        System.out.println("What do you need to do Mr Admin...?");
        do {
            System.out.println("\t\t Press 1 to add a new employee");
            System.out.println("\t\t Press 2 to check the daily income");
            System.out.println("\t\t Press 3 to drug department");
            System.out.println("\t\t Press 4 to product department");
            System.out.println("\t\t Press 5 to stocks");
            System.out.println("\t\t Press 6 to Billing");
            System.out.println("\t\t Press 7 to Add customer & see");
            System.out.println("\t\t Press 8 to see employee bonus");
            System.out.println("\t\t Press 9 to logout");


            choice = in.nextInt();

            switch (choice) {
                case (1):
                    setEmployee();
                    gE1.getEmployeeDetails();
                    showEmployees();
                    break;

                case (2):
                    getIncome();
                    break;

                case (3):
                    Drugs d1= new Drugs();
                    d1.EnterToTheDrugZone();
                    break;

                case (4):
                    Products p1 = new Products();
                    p1.enterToTheProductZone();
                    break;

                case (5):
                    Stock s1 = new Stock();
                    s1.drugQtyWarning();
                    s1.productQtyWarning();
                    s1.search();
                    break;

                case (6):
                    Billing b1 = new Billing();
                    b1.startBilling();
                    break;

                case(7):
                    gC1.setCusDetails();
                    showCustomers();
                    break;
                case(8):
                    e1.setEmployeeBonus();
                    break;

                case (9):
                    goodBye();
                    clearDatabases();
                    choice = 0;
                    break;

                default:
                    System.out.println("Wrong input, try again....");
            }
        } while (choice != 0);

    }

    private void clearDatabases() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from total");
            int i = preparedStatement.executeUpdate();

            PreparedStatement delete_from_bonus = connection.prepareStatement("delete from bonus");
            int j= preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("databases are cleared (Total)");
    } //done

    private void getIncome() {
        int tot=0;
        int i=0;
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from total");

            while (resultSet.next()){
                double tot1 = resultSet.getInt("total");
                System.out.println("Bill no "+i+" : "+tot1);
                i++;
                tot+=tot1;
            }
            System.out.println("-----------------------------------------------");
            System.out.println(CurrentDateTime()+"\t your total is = "+tot);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //done

    public void setEmployee() {
        System.out.println("Now add your employee details...");

        System.out.println("Employee ID: ");
        String id = in.next();
        e1.setEmID(id);

        System.out.print("Employee name: ");
        String eMname = in.next();
        e1.setEmName(eMname);

        System.out.print("Employee contact: ");
        int eContact = in.nextInt();
        e1.setEmContact(eContact);

        System.out.print("Employee Address: ");
        String eAdd = in.next();
        e1.setEmAddress(eAdd);

        System.out.print("Employee DOB: ");
        String eDOB = in.next();
        e1.setEmDOB(eDOB);

        System.out.print("Employee salary: ");
        int eSalary = in.nextInt();
        e1.setSalary(eSalary);




    } //done

    public void showEmployees() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from employee");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String dob = resultSet.getString("dob");
                int contact = resultSet.getInt("contact");
                int salary = resultSet.getInt("salary");
                System.out.println("| id: "+id+" | name: "+name+" | address: "+address+" | dob: "+dob+" | contact: "+contact+" | salary: "+salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } //done

    public void goodBye() {
        System.out.println(" Thank You..! See you soon Mr Admin.... ");
    } //done

    private class getEmployee {
        String emID;
        String emName;
        String emAddress;
        String emDOB;
        int emContact;
        int salary;

        public getEmployee(String emID, String emName, String emAddress, String emDOB, int emContact, int salary) {
            this.emID = emID;
            this.emName = emName;
            this.emAddress = emAddress;
            this.emDOB = emDOB;
            this.emContact = emContact;
            this.salary = salary;
        }

        public getEmployee() {

        }

        public void getEmployeeDetails() {
            emID = e1.getEmID();
            emAddress = e1.getEmAddress();
            emName = e1.getEmName();
            emDOB = e1.getEmDOB();
            emContact = e1.getEmContact();
            salary = e1.getSalary();

            Connection connection = DBConnection.getInstance().getConnection();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into employee values (?,?,?,?,?,?)");
                preparedStatement.setObject(1,emID);
                preparedStatement.setObject(2,emAddress);
                preparedStatement.setObject(3,emName);
                preparedStatement.setObject(4,emDOB);
                preparedStatement.setObject(5,emContact);
                preparedStatement.setObject(6,salary);

                int i = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } //done

    private class getCustomer {
        String cID;
        String cName;
        String cAddress;
        String cDOB;
        int cContact;

        public getCustomer(String cID, String cName, String cAddress, String cDOB, int cContact) {
            this.cID = cID;
            this.cName = cName;
            this.cAddress = cAddress;
            this.cDOB = cDOB;
            this.cContact = cContact;
        }

        public getCustomer(){ }

        public void setCusDetails() {
            this.cID = c1.getcID();
            this.cName = c1.getcName();
            this.cAddress = c1.getcAddress();
            this.cDOB = c1.getcDOB();
            this.cContact = c1.getcContact();

            Connection connection = DBConnection.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into customer values(?,?,?,?,?)");
                preparedStatement.setObject(1,cID);
                preparedStatement.setObject(2,cName);
                preparedStatement.setObject(3,cAddress);
                preparedStatement.setObject(4,cDOB);
                preparedStatement.setObject(5,cContact);

                int i = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } //done

    public void showCustomers(){
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from employee");
            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name =  resultSet.getString("name");
                String address = resultSet.getString("address");
                String dob = resultSet.getString("dob");
                int contact = resultSet.getInt("contact");

                System.out.println("| id: "+id+"| name: "+name+"| address: "+address+"| dob: "+dob+"| contact: "+contact);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //done

    public String CurrentDateTime(){
         {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
             String format = dtf.format(now);
             return format;
        }
    } //done
}







