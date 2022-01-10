package com.eventManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionProvider;

@WebServlet("/AddNewEvent")
public class AddNewEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		String a1=request.getParameter("EventNum");
        String a2=request.getParameter("EventName");
        String a3=request.getParameter("CoordinatorName");
        String a4=request.getParameter("CoordinatorNum");
        String a5 =request.getParameter("Fee");
        String a6=request.getParameter("Venue");
        String a7=request.getParameter("Date");
        
        try{
        	Connection con = ConnectionProvider.getConnection();
			PreparedStatement pstm = con.prepareStatement("insert into eventlist values(?, ?, ?, ?, ?, ?, ?)");
			pstm.setString(1, a1);
			pstm.setString(2, a2);
			pstm.setString(3, a3);
			pstm.setString(4, a4);
			pstm.setString(5, a5);
			pstm.setString(6, a6);
			pstm.setString(7, a7);
			pstm.executeUpdate();
                 
            RequestDispatcher rd=request.getRequestDispatcher("CreateEvent.html");
            rd.include(request, response);
                 
            pw.println("<center><h1> Event Added</h1><center>");
            System.out.println("Added to database!");
            con.commit();
            con.close();
            }catch(Exception exe){System.out.println("Exception caught"+exe);}
	}

}
