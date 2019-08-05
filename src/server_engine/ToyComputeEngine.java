/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_engine;

import client_engine.ComputeInterface;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Raissa
 */

public class ToyComputeEngine extends UnicastRemoteObject implements ComputeInterface {
    
    Connection con = null;
    PreparedStatement stm = null;
    ResultSet rs = null;
    
    public ToyComputeEngine() throws RemoteException {
                
    }
    
    /**
    * Login & Register User - Authenticate User Credentials
    */
    
    //@Override
    @Override
    public boolean userLogin(String user, String pass) throws RemoteException {
        
        boolean found = false;
        con = DBConnect.DBConnect();
        String qry = "SELECT * FROM users WHERE uname = ? AND pass = ?";
        try {
            stm = con.prepareStatement(qry);
            stm.setString(1, user); //bind username to prepared statement
            stm.setString(2, pass); //bind password to prepared statement
            rs = stm.executeQuery(); //execute statement
            
            if(rs.next()) { //check result of query
                found = true;
                return found;
            } else {
                found = false;
                return found;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return found;
    }
    
    //@Override
    @Override
    public boolean userRegister(String names, String uname, String pass) throws RemoteException {
        
        boolean registered = false;
        con = DBConnect.DBConnect();
        String qry = "INSERT INTO users (name, uname, pass) VALUES (?, ?, ?)";
        try {
            stm = con.prepareStatement(qry);
            stm.setString(1, names); //bind names to prepared statement
            stm.setString(2, uname); //bind username to prepared statement
            stm.setString(3, pass); //bind password to prepared statement
            stm.executeUpdate(); //execute statement
            
            registered = true;
            return registered;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return registered;
    }
    
    
    /**
     * Client Tasks - Get toys from DB
     * 1. Delete toy
     * 2. Add new toy
     * 3. Update toy
     * 4. Compute total price of toys 
     */
    
    @Override
    public List<Toy> getToys() throws Exception {
        List<Toy> list = new ArrayList<Toy>();
        
        con = DBConnect.DBConnect();
        String query = "SELECT * FROM toy_price";
        
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        
            //Extract data from result set 
            while(rs.next()) {
                // Retrieve by column name 
                int id  = rs.getInt("id");         
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stockQty = rs.getInt("quantity");

                // Setting the values 
                Toy toy = new Toy();
                toy.setId(id); 
                toy.setName(name); 
                toy.setPrice(price); 
                toy.setStockQty(stockQty);
                list.add(toy);
            }
            rs.close();
            return list;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return list;
    }
    
    @Override
    public boolean deleteToyPrice(int toyId) throws RemoteException {
        
        boolean deleted = false;
        con = DBConnect.DBConnect();
        String qry = "DELETE FROM toy_price where id = ?";
        try {
            stm = con.prepareStatement(qry);
            stm.setInt(1, toyId); //bind id to prepared statement
            stm.executeUpdate(); //execute statement
            
            deleted = true;
            return deleted;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return deleted;
    }
    
    @Override
    public boolean addToyPrice(String name, double price, int stockQty) throws RemoteException {
        
        boolean newToy = false;
        con = DBConnect.DBConnect();
        String qry = "INSERT INTO toy_price (name, price, quantity) VALUES (?, ?, ?)";
        try {
            stm = con.prepareStatement(qry);
            stm.setString(1, name); //bind names to prepared statement
            stm.setDouble(2, price); //bind username to prepared statement
            stm.setInt(3, stockQty); //bind password to prepared statement
            stm.executeUpdate(); //execute statement
            
            newToy = true;
            return newToy;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return newToy;
    }
    
    @Override
    public boolean updateToyPrice(int id, String name, double price, int stockQty) throws RemoteException {
        
        boolean updatedToy = false;
        con = DBConnect.DBConnect();
        String qry = "UPDATE toy_price SET name = ? , price = ? ,"
                + "quantity = ? WHERE id = ?";
        try {
            stm = con.prepareStatement(qry);
            stm.setString(1, name); //bind names to prepared statement
            stm.setDouble(2, price); //bind username to prepared statement
            stm.setInt(3, stockQty); //bind password to prepared statement
            stm.setInt(4, id); //bind password to prepared statement
            stm.executeUpdate(); //execute statement
            
            updatedToy = true;
            return updatedToy;
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return updatedToy;
    }
    
    @Override
    public double calculateToyCost(int id, int qty) throws RemoteException {
        
        double toyCost, price, discPrice = 0.00;
        con = DBConnect.DBConnect();
        String qry = "SELECT * FROM toy_price WHERE id = ?";
        try {
            stm = con.prepareStatement(qry);
            stm.setInt(1, id); //bind id to prepared statement
            rs = stm.executeQuery(); //execute statement
            
            double discount = 0.00;
            
            while(rs.next()) {
                // Retrieve by column name 
                price = rs.getDouble("price");
                toyCost = price * qty;
                
                /**
                 * Compute discounts based on quantity of toys purchased
                 */
                if(qty==20){
                    discount = toyCost * 5/100; // 5% rate for 20 toys
                }else if(qty==40){
                    discount = toyCost * 5/100; // 10% rate for 20 toys
                }else if(qty==60){
                    discount = toyCost * 5/100; // 15% rate for 20 toys
                }else if(qty==80){
                    discount = toyCost * 5/100; // 20% rate for 20 toys
                }else if(qty==100){
                    discount = toyCost * 5/100; // 25% rate for 20 toys
                }else if(qty > 100){
                    discount = toyCost * 5/100; // 25% rate for 20 toys
                }
                discPrice = toyCost - discount;
                // price = price - discount; // substract disounted amount to price
                //return discPrice;
            }
            rs.close();    
            //return discount;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return discPrice;
    }
}
