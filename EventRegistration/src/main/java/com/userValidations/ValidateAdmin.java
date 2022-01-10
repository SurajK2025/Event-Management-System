package com.userValidations;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ValidateAdmin")
public class ValidateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String Username = request.getParameter("AdminUsername");
		String Password = request.getParameter("AdminPassword");
		
		String u1 ="A101", p1 ="1234";
		String u2 ="A102", p2 ="5678";
		String u3 ="A103", p3 ="ABCD";
		String u4 ="A104", p4 ="abcd";

		if(Password.equals(p1)&&Username.equals(u1)){
			HttpSession session=request.getSession();  
	        session.setAttribute("uname", u1);  
			RequestDispatcher rd =request.getRequestDispatcher("AdminEventOptions.html");
			rd.forward(request, response);
		}
		else if(Password.equals(p2)&&Username.equals(u2)){
			HttpSession session=request.getSession();  
	        session.setAttribute("uname", u2); 
			RequestDispatcher rd=request.getRequestDispatcher("AdminEventOptions.html");
			rd.forward(request, response);   
		}
		else if(Password.equals(p3)&&Username.equals(u3)){
			HttpSession session=request.getSession();  
	        session.setAttribute("uname", u3);  
			RequestDispatcher rd=request.getRequestDispatcher("AdminEventOptions.html");
			rd.forward(request, response);  
		}
		else if(Password.equals(p4)&&Username.equals(u4)){
			HttpSession session=request.getSession();  
	        session.setAttribute("uname", u4);  
			RequestDispatcher rd=request.getRequestDispatcher("AdminEventOptions.html");
			rd.forward(request, response);  
		}
		// Credentials incorrect stay on same page only
		else
		{
			pw.println("<center><h1>!! Please Enter Valid Username & Password for Admin !!</h1><center>");
			RequestDispatcher rd=request.getRequestDispatcher("AdminLogin.html");
			rd.include(request, response);
		}
	}

}
