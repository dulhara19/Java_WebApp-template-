package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.PrintWriter;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;





@WebServlet(name = "daoServlet", urlPatterns = {"/daoServlet"})
public class daoServlet extends HttpServlet {

    
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

    // ============--- this servlet is used to send register form data to the database succesfully---==============
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
         
        PrintWriter out= response.getWriter();
        
         String name= request.getParameter("name");
         String pass= request.getParameter("password");
         String checkbox = request.getParameter("checkbox");
         String email= request.getParameter("email"); // not used yet
         String phone= request.getParameter("phone"); // not used yet
         
          
        // define the database 
        String jdbcurl= "jdbc:mysql://localhost:3306/clientdata";
        String dbuser="root"; 
        String dbpass="Lakshan2001mysql";  
        
        // define sql query
        String sql= "INSERT INTO clientname (cname,pass) VALUES (?,?)";
        String sqladmin= "INSERT INTO adminrequest (name,email,phone,adminpass) VALUES (?,?,?,?)";
        
        // Check the value of the checkbox
        if ( checkbox != null) {
            
            
            // Checkbox is checked
            //================================
           try{
  
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      Connection con= DriverManager.getConnection(jdbcurl,dbuser,dbpass);   
      
      PreparedStatement st= con.prepareStatement(sqladmin);
       
       st.setString(1,name);
       st.setString(2,email);
       st.setString(3,phone);
       st.setString(4,pass);
      
      
     // ResultSet rs= st.executeQuery();
      
      
      int rows=st.executeUpdate(); //Using executeQuery for Non-SELECT Statements:
                                   //Make sure to use executeUpdate for any statement 
                                   //that does not return a result set.
                                  
       // check affected raw is greated than 0, then we have successfully updated the db                            
      if( rows>0){
            response.sendRedirect("index.jsp"); 
            
       }
     
      else{
          
      response.sendRedirect("registerlogin.jsp");
       }
      
    }
    catch(ClassNotFoundException | SQLException e){
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
                  }  
            //========================
        } 
          

        else{
        try{
        // check if name or passwords are empty
         if (name.isEmpty() && pass.isEmpty()) { 
             
             //------- if i want to send a value using requestdispacher use, -------
             
          // request.setAttribute("errorMessage", "Empty username or password");
          // request.getRequestDispatcher("register.jsp").forward(request, response);
          
          response.sendRedirect("register.jsp");
           
       }
       
       else{
        
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      Connection con= DriverManager.getConnection(jdbcurl,dbuser,dbpass);   
      
      PreparedStatement st= con.prepareStatement(sql);
       
       st.setString(1,name);
       st.setString(2,pass);
      // st.setString(0,email);
      // st.setString(0,phone);
      
      
     // ResultSet rs= st.executeQuery();
      
      
      int rows=st.executeUpdate(); //Using executeQuery for Non-SELECT Statements:
                                   //Make sure to use executeUpdate for any statement 
                                   //that does not return a result set.
                                   
             
                                 
                                   
                                   
       // check affected raw is greated than 0, then we have successfully updated the db                            
      if( rows>0){
            response.sendRedirect("index.jsp"); 
            
       }
     
      else{
      response.sendRedirect("registerlogin.jsp");
      
       }
      
      }
    }
    catch(ClassNotFoundException | SQLException e){
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
                  }    
     
    }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
