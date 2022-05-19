package Customer_Employee;/*
 * @auther : Dulan M Herath <dulanmherath@outlook.com>
 * @date : 2022 - 05 - 07/05/2022
 * @time : 03:22 pm
 * @project name : SmartPharmacyPOS
 */

import Admin.Administration;
import DB.DBConnection;
import Drugs_Product.Billing;
import com.sun.java.swing.plaf.windows.WindowsBorders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer extends Administration{
    private String cID;
    private String cName;
    private String cAddress;
    private String cDOB;
    private int cContact;

    public Customer(String cID, String cName, String cAddress, String cDOB, int cContact) {
        this.cID = cID;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cDOB = cDOB;
        this.cContact = cContact;
    }

    public Customer() {

    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcDOB() {
        return cDOB;
    }

    public void setcDOB(String cDOB) {
        this.cDOB = cDOB;
    }

    public int getcContact() {
        return cContact;
    }

    public void setcContact(int cContact) {
        this.cContact = cContact;
    }

//-----------------------------------------------------------------------------------------------------------------


}
