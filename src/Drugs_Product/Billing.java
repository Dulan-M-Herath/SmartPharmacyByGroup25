package Drugs_Product;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:23 pm
 * @project name : SmartPharmacyPOS
 */

import Admin.Administration;
import Admin.sayBye;
import DB.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class Billing extends Stock implements sayBye {
   String name;
   double unitPrice;
   int qty;
   double total;
   int newQty;

   int subtotal;

   public void startBilling(){
      int a;
      do{
         System.out.println("Press 1 to add drugs | press 2 to add products | press 3 to print bill");
         a= input.nextInt();
         switch (a){
            case(1) :
               addToBillDrugs();
               break;
            case(2):
               addToBillProducts();
               break;
            case(3):
               printBill();
               break;

            default: return;
         }
      }while(a!=0);


   }

   private void addToBillProducts() {
      //Stock s3 = new Stock();
      String dName;
      System.out.println("No of products: ");
      int noOfProducts = input.nextInt();

      for (int i = 0; i<noOfProducts; i++) {
         System.out.println("enter name: ");
         dName = input.next();
         System.out.println("enter quantity: ");
         int x = input.nextInt();

         int qty2 = updateProductQuantity(dName);

         if (qty2 > 0 && x <= qty2) {
            Connection connection = DBConnection.getInstance().getConnection();

            try {
               PreparedStatement preparedStatement = connection.prepareStatement("select * from products where name = ?");
               preparedStatement.setObject(1, dName);
               ResultSet resultSet = preparedStatement.executeQuery();

               while (resultSet.next()) {
                  name = dName;
                  unitPrice = resultSet.getInt("price");
                  newQty = resultSet.getInt("quantity");
                  qty = x;
                  total = unitPrice * x;
                  refreshProducts(dName);
                  addToDB();
               }
            } catch (SQLException e) {
               e.printStackTrace();
            }

         }
      }
   }

   private void refreshProducts(String d_Name) {
      int p = newQty - qty;
      Connection connection = DBConnection.getInstance().getConnection();
      try {
         PreparedStatement preparedStatement = connection.prepareStatement("update products set quantity = ? where name =?");
         preparedStatement.setObject(1,p);
         preparedStatement.setObject(2,d_Name);
         int i = preparedStatement.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void addToBillDrugs(){
      //Stock s2 = new Stock();
      String dName;
      System.out.println("No of drugs: ");
      int noOfDrugs = input.nextInt();

      for (int i = 0; i<noOfDrugs; i++) {
         System.out.println("enter name: ");
         dName = input.next();
         System.out.println("enter quantity: ");
         int x = input.nextInt();

         int qty2 =updateDrugQuantity(dName);

         if (qty2 > 0 && x <= qty2) {
            Connection connection = DBConnection.getInstance().getConnection();

            try {
               PreparedStatement preparedStatement = connection.prepareStatement("select * from drugs where name = ?");
               preparedStatement.setObject(1, dName);
               ResultSet resultSet = preparedStatement.executeQuery();

               while (resultSet.next()) {
                  name = dName;
                  unitPrice = resultSet.getInt("price");
                  newQty = resultSet.getInt("quantity");
                  qty = x;
                  total = unitPrice * x;
                  refreshDrugs(dName);
                  addToDB();
               }
            } catch (SQLException e) {
               e.printStackTrace();
            }

         }else{
            System.out.println("sorry! your request can't be completed...");
         }
      }
   }

   private void addToDB() {
      Connection connection = DBConnection.getInstance().getConnection();

      try {
         PreparedStatement preparedStatement = connection.prepareStatement("insert into billing values(?,?,?,?)");
         preparedStatement.setObject(1,name);
         preparedStatement.setObject(2,unitPrice);
         preparedStatement.setObject(3,qty);
         preparedStatement.setObject(4,total);

         int i = preparedStatement.executeUpdate();
//         PreparedStatement preparedStatement1 = connection.prepareStatement("insert into total values(?)");
//         preparedStatement1.setObject(1,total);

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   private void refreshDrugs(String d_Name) {
      int p = newQty - qty;
      Connection connection = DBConnection.getInstance().getConnection();
      try {
         PreparedStatement preparedStatement = connection.prepareStatement("update drugs set quantity = ? where name =?");
         preparedStatement.setObject(1,p);
         preparedStatement.setObject(2,d_Name);
         int i = preparedStatement.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }

   }

   public void printBill(){
      Connection connection = DBConnection.getInstance().getConnection();
      //System.out.printf("%-10s %-10d %-10d %-6d\n","name","price","quanty","total");
      try {
         ResultSet resultSet = connection.createStatement().executeQuery("select * from billing");

         while (resultSet.next()){
            String name1 = resultSet.getString("name");
            int price1 = resultSet.getInt("price");
            int qty1 = resultSet.getInt("qty");
            int total1 = resultSet.getInt("total");
            subtotal+=total1;

            System.out.printf("%-10s %-10d %-10d %-10d\n",name1,price1,qty1,total1);
         }
         System.out.println(" ");
         System.out.println("=======================================");
         System.out.println("Total is "+subtotal);

      } catch (SQLException e) {
         e.printStackTrace();
      }
      clearBillingDB();
      sayBye sb = new Billing();
      sb.Bye();
   }

   protected void clearBillingDB() {
      Connection connection = DBConnection.getInstance().getConnection();

      try {
         PreparedStatement preparedStatement1 = connection.prepareStatement("insert into total values(?)");
         preparedStatement1.setObject(1,subtotal);
         int j = preparedStatement1.executeUpdate();

         PreparedStatement prepareStatement2 = connection.prepareStatement("delete from billing");
         int i = prepareStatement2.executeUpdate();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      subtotal=0;
   }


   @Override
   public void Bye() {
      System.out.println("Thanks for coming..... have a nice day!");
   }
}
