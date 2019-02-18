package edu.uncc.nbad;

import java.io.*;
import java.sql.*;
import java.util.*;
//import murach.business.Product;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class ProductTable {

    static String url = "jdbc:mysql://localhost:3306/shop";
    static String username = "user";
    static String password = "123";
    
    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;
	
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    
    static {
        try {
            connection = DriverManager.getConnection (url, username, password);
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
    }
    
    private static List<Product> products = null;

    //needs work boi
    public static List<Product> selectProducts() throws SQLException {
                connection = DriverManager.getConnection (url, username, password);
                Statement statement =connection.createStatement();
                
                ResultSet rs = statement.executeQuery("SELECT * FROM products;");
                //issa not done :(
                List<Product> products = new ArrayList<>();
                while(rs.next()){
                    Product nproduct = new Product(rs.getString("code"), rs.getString("description"), rs.getDouble("price"));
                    products.add(nproduct);
                }
                
                return products;
		//throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static Product selectProduct(String productCode) throws SQLException{
                connection = DriverManager.getConnection (url, username, password);
                Statement statement =connection.createStatement();
                
                ResultSet rs2 = statement.executeQuery("SELECT * FROM shop.products WHERE code =" + productCode +";");                
                
                if(rs2 != null)
                    rs2.next();
                        Product rsproduct = new Product(rs2.getString(2), rs2.getString(3), rs2.getDouble(4));
                    return rsproduct;
                       
    }

    public static boolean exists(String productCode) throws SQLException {
 
		connection = DriverManager.getConnection (url, username, password);
                Statement statement =connection.createStatement();
                
                ResultSet rs3 = statement.executeQuery("SELECT * FROM shop.products WHERE code =" + productCode +";");                
                if(rs3 != null){
                    return true;
                }else{
                    return false;}// remove this line and implement the logic
    }
    
    private static void saveProducts(List<Product> products) {
        try {
            String preparedSQL = "INSERT INTO shop.products (code,description,price) VALUES (?,?,?);";
            selectProduct = connection.prepareStatement(preparedSQL);
            Iterator<Product> it = products.iterator();
            while(it.hasNext()){
                Product aproduct = it.next();
                    selectProduct.setString(1, aproduct.getCode());
                    selectProduct.setString(2, aproduct.getDescription());
                    selectProduct.setDouble(3, aproduct.getPrice());
                    selectProduct.addBatch();
                    }selectProduct.executeUpdate();
                        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
            }
       //throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static void insertProduct(Product aproduct) throws SQLException, IOException {
        try {
            String preparedSQL = "INSERT INTO shop.products (code, description, price) VALUES (?,?,?);";
            selectProduct = connection.prepareStatement(preparedSQL);
            selectProduct.setString(1, aproduct.getCode());
            selectProduct.setString(2, aproduct.getDescription());
            selectProduct.setDouble(3, aproduct.getPrice());
            selectProduct.executeUpdate();
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
    }

    public static void updateProduct(Product aproduct) throws SQLException {
 
		connection = DriverManager.getConnection (url, username, password);
                Statement statement =connection.createStatement();
                String c = aproduct.getCode();
                String d = aproduct.getDescription();
                Double p = aproduct.getPrice();
                
                ResultSet rs5 = statement.executeQuery("UPDATE shop.products"
                        + "SET description="+d+", price="+p+" "
                        + "WHERE code ="+c+";");                

    }
    public static void deleteProduct(Product aproduct) throws SQLException {
 
		connection = DriverManager.getConnection (url, username, password);
                Statement statement =connection.createStatement();
                ResultSet rs5 = statement.executeQuery("DELETE products Where code="+aproduct.getCode()+";");
    }    
}