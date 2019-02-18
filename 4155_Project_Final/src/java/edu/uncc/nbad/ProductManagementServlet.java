/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LeeS
 */
public class ProductManagementServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductManagementServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagementServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>"); */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        
        //read action parameter
        String action = request.getParameter("action");
        String url="/products.jsp";
       // String path = getServletContext().getRealPath("/WEB-INF/products.txt");
       
        if (action != null) {
            if (action.equals("displayProducts")) {
                try {
                    // If action is equal to displayProducts show products.jsp
                    url = "/products.jsp";
                    
                    List<Product> products = ProductTable.selectProducts();
                    if(products!= null)
                        session.setAttribute("products", products);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("addProduct")) {
            // If action is equal to addProduct show product.jsp
                url = "/product.jsp";
            } else if (action.equals("displayProduct")) {
                // If action is equal to displayProduct show product.jsp
                url = "/product.jsp";
            } else if (action.equals("deleteProduct")) {
                // If action is equal to deleteProduct show confirmDelete.jsp
                url = "/confirmDelete.jsp";
            } else { url = "/index.jsp"; }
            
             getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
       
        
       
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        //read action parameter
        String action = request.getParameter("action");
        String url = "/products.jsp";
        if (action != null) {
            // DISPLAY PRODUCTS
            if (action.equals("displayProducts")) {
                // If action is equal to displayProducts show products.jsp
                url = "/products.jsp";
                
                // if someone is deleteing, then delete and return to the updated page
                if (request.getParameter("delete") != null) {
                    if (request.getParameter("delete").equalsIgnoreCase("yes")) {
                        try {
                            String productCode = request.getParameter("code");
                            Product product = ProductTable.selectProduct(productCode);
                            if(product!=null)
                                ProductTable.deleteProduct(product);
                        } catch (SQLException ex) {
                            Logger.getLogger(ProductManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
                
                // if someone is updating, then delete and return to the updated page
                if (request.getParameter("update") != null) {
                    if (request.getParameter("update").equalsIgnoreCase("yes")) {
                        try {
                            String productCode = request.getParameter("code");
                            String productDescription = request.getParameter("description");
                            Double productPrice = Double.parseDouble(request.getParameter("price")); 

                            if (ProductTable.exists(productCode)) {
                                Product product = ProductTable.selectProduct(productCode);
                                product.setCode(productCode);
                                product.setDescription(productDescription);
                                product.setPrice(productPrice);
                                ProductTable.updateProduct(product);
                            } else {
                                Product product = new Product();
                                product.setCode(productCode);
                                product.setDescription(productDescription);
                                product.setPrice(productPrice);
                                ProductTable.insertProduct(product); //product.setCode(productCode);
                            }
                        } catch (NumberFormatException ex) {
                            url = "/productManagement?action=displayProduct";
                            request.setAttribute("error", "Make sure that you have not left any fields blank and"
                                    + " filled in the price like: \"15.95\""  + ex);
                        } catch (Exception ex) {
                            
                        }
                    }
                }
                
                // get the products
                //List<Product> products;
                try {
                    List<Product> products = ProductTable.selectProducts();
                    session.setAttribute("products", products);
                } catch (SQLException ex) {
                    //Logger.getLogger(ProductManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } 
            
   
            // DISPLAY PRODUCT
            else if (action.equals("displayProduct")) {
                try {
                    // If action is equal to displayProduct show product.jsp
                    url = "/product.jsp";
                    
                    String productCode = request.getParameter("productCode");
                    Product product = ProductTable.selectProduct(productCode);
                    if(product!=null)
                        request.setAttribute("product", product);
                } catch (SQLException ex) {
                    //Logger.getLogger(ProductManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
           
            // DELETE PRODUCT
            else if (action.equals("deleteProduct")) {
                try {
                    // If action is equal to deleteProduct show confirmDelete.jsp
                    url = "/confirmDelete.jsp";
                    String productCode = request.getParameter("productCode");
                    Product product = ProductTable.selectProduct(productCode);
                    if (product!=null)
                        request.setAttribute("product", product);
                } catch (SQLException ex) {
                    Logger.getLogger(ProductManagementServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            else { url = "/index.jsp"; }
            
             getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
        }
       
    }
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
