
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.sql.SQLException;



public class daoServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
         String name= request.getParameter("name");
         String pass= request.getParameter("password");
          
        
        String jdbcurl= "jdbc:mysql://localhost:3306/clientdata";
        String dbuser="root";
        String dbpass="Lakshan2001mysql";
        
        String sql= "INSERT INTO clientname (cname,pass) VALUES (?,?)";
        

    try{
        
       if (name.isEmpty() && pass.isEmpty()) { response.sendRedirect("index.html");}
       
       else{
        
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con= DriverManager.getConnection(jdbcurl,dbuser,dbpass);   
      
      PreparedStatement st= con.prepareStatement(sql);
       
       st.setString(1,name);
       st.setString(2,pass);
      
      st.executeUpdate();
      response.sendRedirect("loginPg.jsp");
      
       }
      
    }
    catch(ClassNotFoundException | SQLException e){
            out.println("<html><body>");
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            out.println("</body></html>");
                  }    
       
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
