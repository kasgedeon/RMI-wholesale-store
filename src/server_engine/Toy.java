/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_engine;

import java.io.Serializable;

/**
 *
 * @author Raissa
 */
public class Toy implements Serializable {
    
    private int id, stockQty;
    private String name;
    private double price;
    
    /* 
    Setters and getters method for a toy
    */
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getStockQty(){
        return stockQty;
    }
    
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setStockQty(int stockQty){
        this.stockQty = stockQty;
    }
}
