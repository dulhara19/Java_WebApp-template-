import dao.AdminRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminRequests")
public class adminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AdminRequest> adminRequestList = new ArrayList<>();

        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/clientdata";
            String dbUser = "root";
            String dbPassword = "Lakshan2001mysql";

            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            String sql = "SELECT aid, name, phone, email FROM adminrequest";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                AdminRequest adminRequest = new AdminRequest();
                adminRequest.setAid(resultSet.getInt("aid"));
                adminRequest.setName(resultSet.getString("name"));
                adminRequest.setPhone(resultSet.getString("phone"));
                adminRequest.setEmail(resultSet.getString("email"));
                adminRequestList.add(adminRequest);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("adminRequestList", adminRequestList);
        request.getRequestDispatcher("adminDashBoard.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int aid = Integer.parseInt(request.getParameter("aid"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/clientdata";
            String dbUser = "root";
            String dbPassword = "Lakshan2001mysql";
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            if ("accept".equals(action)) {
                String sqlInsert = "INSERT INTO admindata (name, phone, email) VALUES (?, ?, ?)";
                PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
                statementInsert.setString(1, name);
                statementInsert.setString(2, phone);
                statementInsert.setString(3, email);
                statementInsert.executeUpdate();

                String sqlDelete = "DELETE FROM adminrequest WHERE aid = ?";
                PreparedStatement statementDelete = connection.prepareStatement(sqlDelete);
                statementDelete.setInt(1, aid);
                statementDelete.executeUpdate();
            } else if ("delete".equals(action)) {
                String sqlDelete = "DELETE FROM adminrequest WHERE aid = ?";
                PreparedStatement statementDelete = connection.prepareStatement(sqlDelete);
                statementDelete.setInt(1, aid);
                statementDelete.executeUpdate();
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("adminRequests");
    }
}
