package Drugs_Product;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:22 pm
 * @project name : SmartPharmacyPOS
 */

import Admin.Administration;
import Admin.sayBye;
import DB.DBConnection;
import Drugs_Product.Drugs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Stock implements sayBye {

    int choice;
    Scanner input = new Scanner(System.in);

    public void search(){
        do{
            System.out.println("Press 1 for drugs | press 2 for products | 9 to terminate");
            choice = input.nextInt();

            switch (choice){
                case(1):
                    searchDrugs();
                    break;

                case(2):
                    searchProducts();
                    break;

                case(9):
                    sayBye sb = new Stock();
                    sb.Bye();
                    choice=0;
            }
        }while (choice!=0);
    }

    private void searchProducts() {
        System.out.println("Enter product name to search: ");
        String s = input.next();

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where name = ?");
            preparedStatement.setObject(1,s);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");

                System.out.println("|name: "+name+" | price: "+price+" | quantity: "+quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void searchDrugs() {
        System.out.println("Enter drug name to search: ");
        String s = input.next();

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from drugs where name = ?");
            preparedStatement.setObject(1,s);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int quantity = resultSet.getInt("quantity");

                int weight = resultSet.getInt("weight");

                System.out.println("|name: "+name+" | price: "+price+" | quantity: "+quantity+" | weight: "+weight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int updateDrugQuantity(String name){
        Connection connection = DBConnection.getInstance().getConnection();
        int qty=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from drugs where name = ?");
            preparedStatement.setObject(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                 qty = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }

    public int updateProductQuantity(String name){
        Connection connection = DBConnection.getInstance().getConnection();
        int qty=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from drugs where name = ?");
            preparedStatement.setObject(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                qty = resultSet.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;

    }

    public void drugQtyWarning(){
        int qty;
        String name;

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from drugs");
            while (resultSet.next()){
                qty = resultSet.getInt("quantity");
                name = resultSet.getString("name");
                if(qty==0){
                    System.out.println(name+" stock is empty");
                }else if(qty<10) System.out.println("Lack of stock in "+name+" (remaining: "+qty+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void productQtyWarning(){
        int qty;
        String name;

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select * from products");
            while (resultSet.next()){
                qty = resultSet.getInt("quantity");
                name = resultSet.getString("name");
                if(qty==0){
                    System.out.println(name+" stock is empty");
                }else if(qty<10) System.out.println("Lack of stock in "+name+" (remaining: "+qty+")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void Bye() {
        System.out.println("Thanks for coming to the drug department");
    }
}
