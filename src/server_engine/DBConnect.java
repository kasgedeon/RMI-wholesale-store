/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_engine;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Raissa
 */
public class DBConnect {
    
    Connection con = null;
    
    public static Connection DBConnect(){
        try{            
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Class.forName("com.mysql.jdbc.Driver");
            
            //Open a connection
            System.out.println("Connecting to a selected database...");            
            String databaseURL = "jdbc:mysql://localhost:3306/wholesale_toy_service"; //port to mysql is 3306
            Connection con = DriverManager.getConnection(databaseURL, "root", "");
            System.out.println("Connected database successfully...");
            //JOptionPane.showMessageDialog(null, "Connected to DB!");
            
            System.out.println("Execution of Client tasks...");
            return con;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
}
