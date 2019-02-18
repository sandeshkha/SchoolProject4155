package edu.uncc.nbad;

import java.io.*;
import java.util.*;
//import murach.business.User;

public class UserIO {

    public static void addRecord(User user, String filename) throws IOException {
        File file = new File(filename);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
        out.println(user.getFirstName() + "|"
                + user.getLastName() + "|"
                + user.getEmail() + "|"
                + user.getPassword());
        out.close();
    }

    public static User getUser(String emailAddress, String filename) throws IOException {
        
        User user = null;
        File file = new File(filename);
        boolean exist = false;
        
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            
            user = new User();

            String line = in.readLine();
            while (line != null) {
                StringTokenizer t = new StringTokenizer(line, "|");
                String firstName = t.nextToken();
                String lastName = t.nextToken();
                String email = t.nextToken();
                if (email.equalsIgnoreCase(emailAddress)) {
                    String password = t.nextToken();
                    user.setEmail(emailAddress);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);
                    exist = true;
                }
                line = in.readLine();
            }
            in.close();
        }
        if (exist){
            return user;
        } else {
            return null;
        }
    }

    public static ArrayList<User> getUsers(String filename) throws IOException {
        ArrayList<User> users = new ArrayList<User>();
        File file = new File(filename);
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            String line = in.readLine();
            while (line != null) {
                try {
                    StringTokenizer t = new StringTokenizer(line, "|");
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String emailAddress = t.nextToken();
                    String password = t.nextToken();
                    User user = new User(firstName, lastName, emailAddress, password);
                    users.add(user);
                    line = in.readLine();
                } catch (NoSuchElementException e) {
                    line = in.readLine();
                }
            }
            in.close();
        }
        return users;
    }

    public static HashMap<String, User> getUsersMap(String filename) throws IOException {
        HashMap<String, User> users = new HashMap<String, User>();
        File file = new File(filename);
        if(file.exists())
        {
            BufferedReader in = new BufferedReader(
                    new FileReader(file));
            String line = in.readLine();
            while (line != null) {
                try {
                    StringTokenizer t = new StringTokenizer(line, "|");
                    String firstName = t.nextToken();
                    String lastName = t.nextToken();
                    String emailAddress = t.nextToken();
                    String password = t.nextToken();
                    User user = new User(firstName, lastName, emailAddress,password);
                    users.put(emailAddress, user);
                    line = in.readLine();
                } catch (NoSuchElementException e) {
                    line = in.readLine();
                }
            }
            in.close();
        }
        return users;
    }
}
