package Customer_Employee;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:22 pm
 * @project name : SmartPharmacyPOS
 */

import Admin.Administration;
import DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Administration {

     private String emID;
     private String emName;
     private String emAddress;
     private String emDOB;
     private int emContact;
     private int salary;

    public Employee() {
    }

    public Employee(String emID, String emName, String emAddress, String emDOB, int emContact, int salary) {
        this.setEmID(emID);
        this.setEmName(emName);
        this.setEmAddress(emAddress);
        this.setEmDOB(emDOB);
        this.setEmContact(emContact);
        this.setSalary(salary);
    }


    public String getEmID() {
        return emID;
    }

    public void setEmID(String emID) {
        this.emID = emID;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public String getEmAddress() {
        return emAddress;
    }

    public void setEmAddress(String emAddress) {
        this.emAddress = emAddress;
    }

    public String getEmDOB() {
        return emDOB;
    }

    public void setEmDOB(String emDOB) {
        this.emDOB = emDOB;
    }

    public int getEmContact() {
        return emContact;
    }

    public void setEmContact(int emContact) {
        this.emContact = emContact;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setEmployeeBonus(){
        int bonus=0;
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from total");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int bb = resultSet.getInt("total");
                bonus+=(bb*0.5);
            }
            System.out.println(CurrentDateTime()+"; Bonus is: "+bonus);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
