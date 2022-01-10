package com.userValidations;

import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionProvider;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String fname = request.getParameter("Fname");
		String uname = request.getParameter("Uname");
		String pass = request.getParameter("Password");
		String cpass = request.getParameter("Cpassword");
		
		if(cpass.equals(pass)) {		
			try {				
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement pstm = con.prepareStatement("insert into userinfo(fname, uname, pass) values(?, ?, ?)");
				pstm.setString(1, fname);
				pstm.setString(2, uname);
				pstm.setString(3, pass);
				pstm.executeUpdate();
				
				RequestDispatcher rd = request.getRequestDispatcher("ParticipantEvent.html");
				rd.forward(request, response);
				con.commit();
	            con.close();
			}
			catch(Exception e){	System.out.println(e); }
		}
		else {
			pw.println("<center><h1>!! Both password fileds should match. !!</h1><center>");
            RequestDispatcher rd=request.getRequestDispatcher("ParticipantSignup.html");
            rd.include(request, response);
		}
				
	}

}
