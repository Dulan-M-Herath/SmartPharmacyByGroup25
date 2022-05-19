package Drugs_Product;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:23 pm
 * @project name : SmartPharmacyPOS
 */

import Admin.Administration;
import Admin.sayBye;
import DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Drugs implements sayBye {
    Scanner input = new Scanner(System.in);

    String DrugID;
    String DrugName;
    String DrugBrand;
    double weight;
    double price;
    int quantity;


    public Drugs(){

    }
    public Drugs(String drugID, String drugName, String drugBrand, double weight, double price, int quantity) {
        this.DrugID = drugID;
        this.DrugName = drugName;
        this.DrugBrand = drugBrand;
        this.weight = weight;
        this.price = price;
        this.quantity = quantity;
    }

    public void EnterToTheDrugZone() {
        int y;
        do {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("-------------Welcome to the Drugs department---------------");
            System.out.println("press 1 to add a new drug");
            System.out.println("press 2 to update a drug");
            System.out.println("press 3 to see all drugs");
            System.out.println("press 9 to terminate");
            System.out.println("Enter the choice: ");
            y = input.nextInt();

            switch (y) {

                case (1):
                    addnew();
                    break;
                case(2):
                    update();
                    break;

                case (3):
                    showDrugs();
                    break;

                case (9):
                    sayBye sb = new Drugs();
                    sb.Bye();
                    y=0;
                    break;

                default:
                    System.out.println("Wrong input....");
            }
        }while(y!=0);


    }

    private void showDrugs() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from drugs");

            while (resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                int weight = resultSet.getInt("weight");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println("| id: "+id+"| name: "+name+"| brand: "+brand+"| weight: "+weight+"| price: "+price+"| quantity: "+quantity);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addnew() {
        System.out.println("Drug ID is: ");
        String di = input.next();
        this.DrugID = di;

        System.out.println("Drug name: ");
        String dn = input.next();
        this.DrugName = dn;

        System.out.println("Drug brand: ");
        String db = input.next();
        this.DrugBrand = db;

        System.out.println("Weight: ");
        double dw = input.nextDouble();
        this.weight = dw;

        System.out.println("Price: ");
        double dp = input.nextDouble();
        this.price = dp;

        System.out.println("Quantity: ");
        int dq = input.nextInt();
        this.quantity = dq;


        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into drugs values (?,?,?,?,?,?)");
            preparedStatement.setObject(1,DrugID);
            preparedStatement.setObject(2,DrugName);
            preparedStatement.setObject(3,DrugBrand);
            preparedStatement.setObject(4,weight);
            preparedStatement.setObject(5,price);
            preparedStatement.setObject(6,quantity);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("enter name to update: ");
        String s = input.next();
        int choice;
        do {
            System.out.println("press 1 to change name | 2 to change quantity | 3 to change price | 0 to terminate");
            choice = input.nextInt();

            switch (choice) {

                case(1):
                    System.out.println("Enter new name: ");
                    String sNew = input.next();
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("update drugs set name = ? where name = ?");
                    preparedStatement.setObject(1, sNew);
                    preparedStatement.setObject(2, s);

                    int i = preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

                case(2):
                    System.out.println("Enter new quantity: ");
                    String qNew = input.next();
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("update drugs set quantity = ? where name = ? ");
                        preparedStatement.setObject(1,qNew);
                        preparedStatement.setObject(2,s);

                        int i = preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case(3):
                    System.out.println("Enter new price: ");
                    int pNew = input.nextInt();

                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement("update drugs set price = ? where name = ?");
                        preparedStatement.setObject(1,pNew);
                        preparedStatement.setObject(2,s);

                        int i = preparedStatement.executeUpdate();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case(0): choice=0;

            }

        }while(choice!=0);


    }

    public void Bye(){
        System.out.println("Thanks for coming to the drug department");
    }

}
