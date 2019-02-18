/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//import murach.business.Product;

/**
 *
 * @author lsafarne
 */
public class ProductIO {

    private static List<Product> products = null;


    public static List<Product> selectProducts(String filename) {
        products = new ArrayList<Product>();
        File file = new File(filename);
        try {
                if(file.exists())
                {
                    BufferedReader in
                            = new BufferedReader(
                                    new FileReader(file));

                    String line = in.readLine();
                    while (line != null) {
                        StringTokenizer t = new StringTokenizer(line, "|");
                        if (t.countTokens() >= 3) {
                            String code = t.nextToken();
                            String description = t.nextToken();
                            String priceAsString = t.nextToken();
                            double price = Double.parseDouble(priceAsString);

                            Product p = new Product();
                            p.setCode(code);
                            p.setDescription(description);
                            p.setPrice(price);

                            products.add(p);
                        }
                        line = in.readLine();
                    }
                    in.close();
                }
            return products;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static Product selectProduct(String productCode,String filename) {
        products = selectProducts(filename);
        for (Product p : products) {
            if (productCode != null
                    && productCode.equalsIgnoreCase(p.getCode())) {
                return p;
            }
        }
        return null;
    }

    public static boolean exists(String productCode,String filename) {
        Product p = selectProduct(productCode, filename);
        if (p != null) return true;
        else return false;
    }    
    
    private static void saveProducts(List<Product> products,String filename) {
        try {
            File file = new File(filename);
            PrintWriter out
                    = new PrintWriter(
                            new FileWriter(file));

            for (Product p : products) {
                out.println(p.getCode() + "|"
                        + p.getDescription() + "|"
                        + p.getPrice());
            }

            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void insertProduct(Product product,String filename) {
        products = selectProducts(filename);
        products.add(product);
        saveProducts(products,filename);
    }

    public static void updateProduct(Product product,String filename) {
        products = selectProducts(filename);
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product.getCode() != null
                    && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.set(i, product);
            }
        }
        saveProducts(products,filename);
    }

    public static void deleteProduct(Product product, String filename) {
        products = selectProducts(filename);
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (product != null
                    && product.getCode().equalsIgnoreCase(p.getCode())) {
                products.remove(i);
            }
        }
        saveProducts(products,filename);
    }    
}
