package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		try{
    			PrintWriter out=response.getWriter();
    			Class.forName("com.mysql.cj.jdbc.Driver");
    			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userlogin","root","sasumithu");
    			String na=request.getParameter("txtName");
    			String pw=request.getParameter("txtPwd");
    			PreparedStatement prstmt= con.prepareStatement("select username from login where username=? and password=?");
    			prstmt.setString(1,na);
    			prstmt.setString(2, pw);
    			ResultSet rs=prstmt.executeQuery();
    			if(rs.next()) {
    				
    				RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");
    				rd.forward(request, response);
    				/*out.println("<font color=red size=18>Login Successful!!<br>");
    				out.println("<a href=login.jsp color= Black> Logout</a>");*/
    			}
    			else
    			{
    				out.println("<font color=red size=18>Login Failed!!<br>");
    				out.println("<a href=login.jsp> Try Again</a>");
    			}
    			
    		}
    		catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
