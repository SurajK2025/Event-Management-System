package com.eventManagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.connection.ConnectionProvider;

import java.sql.*;

@WebServlet("/RegisterForEvent")
public class RegisterForEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        
        String a1=request.getParameter("cardno");
        String a2=request.getParameter("edate");
        String a3=request.getParameter("cvv");
        String a4=request.getParameter("cname");
        String a5=request.getParameter("enum");
        String a6=request.getParameter("ename");
        
        String Qs="insert into transhis values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"') ";
        
        try{
        	Connection con = ConnectionProvider.getConnection();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(Qs);
            
            con.commit();
            con.close();
            
            RequestDispatcher rd=request.getRequestDispatcher("Payment.html");
            rd.forward(request,response);  
            
            
       }catch(Exception exe){
    	   System.out.println("Exception caught"+exe);
       }
	}

}
