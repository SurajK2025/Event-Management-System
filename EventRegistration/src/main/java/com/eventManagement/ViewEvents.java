package com.eventManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionProvider;

@WebServlet("/ViewEvents")
public class ViewEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Event  Page</title>");
        pw.println("<link rel=\"stylesheet\" href=\"CSS/final.css\">");
        pw.println("<link href=\"https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap\" rel=\"stylesheet\">");
        pw.println("</head>");
        pw.println("<body>");
        try{
        	
        	Connection con = ConnectionProvider.getConnection();
        	PreparedStatement pstm = con.prepareStatement("select * from EventList");
            ResultSet rs = pstm.executeQuery(""); 
                 
            
            pw.println("<center><h1>Event Details</h1></center>");
            pw.println("<br>");
            pw.println("<div>");
                 
            pw.println("<center>");
            pw.println("<table border=1 width=50% height=50%>");  
            pw.println("<tr><th>EventNumber</th><th>EventName</th><th>Coordinator</th><th>Coordinator Contact</th><th>Fees</th><th>Venue</th><th>Date</th>");  
            while (rs.next()) 
            {  
                String n = rs.getString("EvntNum");  
                String nm = rs.getString("EvntName");  
                String co = rs.getString("EvntCord");
                String cono  = rs.getString("EvntCordNum");
                String f=rs.getString("EvntFee");
                String v=rs.getString("EvntVenu");
                String d=rs.getString("EvntDate");
                pw.println("<tr><td>" + n + "</td><td>" + nm +"</td><td>"+co+"</td><td>"+cono+"</td><td>"+f+"</td><td>"+v+"</td><td>"+d+"</td></tr>");   
            }  
            con.commit();
            con.close(); 
            pw.println("</table>"); 
            pw.println("</center>");
            pw.print("</body>");
            pw.print("</html>"); 
        } catch(Exception e){System.out.println("Exception caught "+e);}
	}

}
