/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_engine;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Raissa
 */
public class ToyComputeTaskRegistry {
    
    public static void main(String args[]) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            ToyComputeEngine tye = new ToyComputeEngine();
            reg.rebind("wholesale_toy_server", tye);
            System.out.println("Server is up...");
        } catch(Exception ex) {
            ex.printStackTrace();
        }        
    }    
}
