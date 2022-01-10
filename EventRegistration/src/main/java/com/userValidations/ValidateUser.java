package com.userValidations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.ConnectionProvider;


@WebServlet("/ValidateUser")
public class ValidateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstm = con.prepareStatement("select * from userinfo where uname='"+username+"' and pass='"+password+"'");
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				HttpSession session=request.getSession();  
		        session.setAttribute("uname", username);  
		        pw.print("<center><h1>Welcome "+ username+"</h1></center>");  
				RequestDispatcher rd=request.getRequestDispatcher("ParticipantEvent.html");  
		        rd.include(request,response);  
			}
			else {
				pw.print("<h1>Sorry username or password incorrect</h1>");  
		        RequestDispatcher rd=request.getRequestDispatcher("ParticipantLogin.html");  
		        rd.include(request,response);  
			}
			
			con.commit();
            con.close();
			
		}catch(Exception e) {
			
		}
	}

}
