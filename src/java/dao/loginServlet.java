
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); 
            out.println("<title>Servlet loginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         response.setContentType("text/html;charset=UTF-8");
         
         PrintWriter out= response.getWriter();
         
         String lname= request.getParameter("username");    /* retrive values from user*/
         String lpassword= request.getParameter("password");
         
         
        String jdbcURL = "jdbc:mysql://localhost:3306/clientdata";
        String dbUser = "root";  
        String dbPassword = "Lakshan2001mysql";
        
        String sql1 = "SELECT * FROM clientname WHERE cname = ? AND pass = ? ";
        String adminsql = "SELECT * FROM admindata WHERE  name = ? AND adminPass = ? ";
        
         
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
             // Establish connection
            Connection con= DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // create statemenet
            PreparedStatement st= con.prepareStatement(sql1);
            PreparedStatement st1= con.prepareStatement(adminsql);
            
            // set param value to the sql query
            st.setString(1,lname);
            st.setString(2,lpassword);
            
            st1.setString(1,lname);
            st1.setString(2,lpassword);
            
            // creating resultset           
            ResultSet rs= st.executeQuery();
            ResultSet rs1= st1.executeQuery();
            
            if(rs1.next() ){
                
                  response.sendRedirect("adminDashBoard.jsp");
            
            }
            else{   // if he is not a super admin then,
                
                 // Check if the result set has any rows and matching pass and name for users
            if (rs.next()) {
                
                // If valid credentials, redirect to success page
                response.sendRedirect("index.jsp");
                
                
            } else {
                
                // If invalid credentials, redirect to error page and show alert
                  request.setAttribute("errorMessage", "Invalid username or Password");
                  request.getRequestDispatcher("registerlogin.jsp").forward(request, response);
                
            }
           } 

        } catch(ClassNotFoundException | SQLException e){
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
                  } 
    }
}
