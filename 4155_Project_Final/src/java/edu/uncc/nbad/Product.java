/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.Serializable;

/**
 *
 * @author LeeS
 */
public class Product implements Serializable{
    private String code, description;
    private double price;
    
    public Product () {
        code = "";
        description = "";
        price = 0;
    }
    
    public Product (String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }
    
    public String getCode () {
        return code;
    }
    
    public String getDescription () {
        return description;
    }
    
    public double getPrice () {
        return price;
    }
    
    public void setCode (String code) {
        this.code = code;
    }
    
    public void setDescription (String description) {
        this.description = description;
    }
    
    public void setPrice (double price) {
        this.price = price;
    }
}
