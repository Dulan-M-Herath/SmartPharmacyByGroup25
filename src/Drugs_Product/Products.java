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

public class Products implements sayBye {
     String productID;
     String productName;
     int price;
     String brand;
     int quantity;

     Scanner input = new Scanner(System.in);

    public Products() {
    }


    public Products(String productID, String productName, int price, String brand, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    public void enterToTheProductZone(){
        int y;
        do {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("-------------Welcome to the Product department---------------");
            System.out.println("press 1 to add a new product");
            System.out.println("press 2 to update a product");
            System.out.println("press 3 to see all product");
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
                    showProducts();
                    break;

                case (9):
                    sayBye a1 = new Products();
                    a1.Bye();
                    y=0;
                    break;

                default:
                    System.out.println("Wrong input....");
                    break;
            }
        }while(y!=0);
    }

    private void update() {

    }

    private void showProducts() {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from products");

            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println("| id: "+id+"| name: "+name+"| brand: "+brand+"| price: "+price+"| quantity: "+quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //done

    private void addnew() {
        System.out.println("product id is : ");
        String pID = input.next();
        this.productID = pID;

        System.out.println("Product name: ");
        String productName = input.next();
        this.productName = productName;

        System.out.println("Price: ");
        int price = input.nextInt();
        this.price = price;

        System.out.println("Brand: ");
        String brand = input.next();
        this.brand=brand;

        System.out.println("Quantity: ");
        int quantity = input.nextInt();
        this.quantity = quantity;

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into products values(?,?,?,?,?)");
            preparedStatement.setObject(1,productID);
            preparedStatement.setObject(2,productName);
            preparedStatement.setObject(3,price);
            preparedStatement.setObject(4,brand);
            preparedStatement.setObject(5,quantity);

            int i = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void Bye() {
        System.out.println("Thanks For coming to the products zone...");
    }
}
