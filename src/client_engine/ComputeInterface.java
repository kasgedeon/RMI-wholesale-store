/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_engine;

import java.rmi.*;
import java.util.*;
import server_engine.Toy;

/**
 *
 * @author Raissa
 */
public interface ComputeInterface extends Remote {
    
    public boolean userLogin(String user, String pass) throws RemoteException;
    
    public boolean userRegister(String names, String uname, String pass) throws RemoteException;
    
    public List<Toy> getToys() throws Exception;
    
    public boolean deleteToyPrice(int toyId) throws RemoteException;
    
    public boolean addToyPrice(String name, double price, int stockQty) throws RemoteException;
    
    public boolean updateToyPrice(int id, String name, double price, int stockQty) throws RemoteException;
    
    public double calculateToyCost(int id, int qty) throws RemoteException;
}
