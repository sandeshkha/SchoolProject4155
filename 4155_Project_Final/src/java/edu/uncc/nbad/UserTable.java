package edu.uncc.nbad;

import java.io.*;
import java.util.*;
import java.sql.*;
//import murach.business.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UserTable {
    
    static String url = "jdbc:mysql://localhost:3306/shop";
    static String username = "user";
    static String password = "123";
    
    static Connection connection = null;
    static PreparedStatement selectProduct = null;
    static ResultSet resultset = null;
	
	//Static initializer, it runs when the class is intialized (it is executed once)
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
    
    public static void addRecord(User user) throws IOException {
        try {
            String preparedSQL = "INSERT INTO shop.users (firstName,lastName,email,password) VALUES (?,?,?,?);";
            selectProduct = connection.prepareStatement(preparedSQL);
            selectProduct.setString(1, user.getFirstName());
            selectProduct.setString(2, user.getLastName());
            selectProduct.setString(3, user.getEmail());
            selectProduct.setString(4, user.getPassword());
            selectProduct.executeUpdate();
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
		//throw new NotImplementedException(); // remove this line and implement the logic

    }

    public static User getUser(String emailAddress) throws IOException {
        User user = new User();
        boolean sqlWork = false;
        try {
            String preparedSQL = "SELECT firstName,lastName,email,password FROM shop.users WHERE email = ?;";
            selectProduct = connection.prepareStatement(preparedSQL);
            selectProduct.setString(1, emailAddress);
            resultset = selectProduct.executeQuery();
            
            while (resultset.next()) {
                user.setFirstName(resultset.getString("firstName"));
                user.setLastName(resultset.getString("lastName"));
                user.setEmail(resultset.getString("email"));
                user.setPassword(resultset.getString("password"));
            }
            sqlWork = true;
        }
        catch (SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        }
        if (!sqlWork) {
            user = null;
        }
        return user;
        //throw new NotImplementedException(); // remove this line and implement the logic

    }

    public static ArrayList<User> getUsers() throws IOException, SQLException {
            connection = DriverManager.getConnection (url, username, password);
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM shop.users;");

            ArrayList<User> users = new ArrayList<>();
                while(rs.next()){
                    User nuser = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                    users.add(nuser);
                }
                return users;
//throw new NotImplementedException(); // remove this line and implement the logic
    }

    public static HashMap<String, User> getUsersMap() throws IOException, SQLException {
            connection = DriverManager.getConnection (url, username, password);
            Statement statement =connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM shop.users;");
            HashMap<String, User> users = new HashMap<String, User>();
            while(rs.next()){
                User nuser = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"));
                users.put(rs.getString("email"), nuser);
            }
            return users;
    }
}
