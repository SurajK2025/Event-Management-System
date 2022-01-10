package com.transactionManagement;

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

@WebServlet("/TransactionHis")
public class TransactionHis extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
         pw.println("<!DOCTYPE html>");
         pw.println("<html>");
         pw.println("<head>");
         pw.println("<title> Transactions Page</title>");
         pw.println("<link rel=\"stylesheet\" href=\"total.css\">");
         pw.println("<link href=\"https://fonts.googleapis.com/css2?family=Balsamiq+Sans&display=swap\" rel=\"stylesheet\">");
         pw.println("</head>");
         pw.println("<body>");
         
         try{
        	 Connection con = ConnectionProvider.getConnection();
         	 PreparedStatement pstm = con.prepareStatement("select * from TransHis");
             ResultSet rs = pstm.executeQuery("");
             
             pw.println("<center><h1> Transaction  Details </h1> </center> ");
            
             pw.println("<div>");
            
             pw.println("<center>");
             pw.println("<table border=1 width=50% height=50%>");  
             pw.println("<tr><th>Event No</th><th>Event Name</th><th>User Name</th><th>Card Number</th>");  
         
             while (rs.next()) 
               {  
                  
                String en = rs.getString("EvntNum");
                String re = rs.getString("EvntName");
                String pd  = rs.getString("CardNum");
                String name =rs.getString("CardName");
               
                pw.println("<tr><td>" + en + "</td><td>" + re + "</td><td>"+name+"</td><td>" + pd +"</td></tr>");   
               }
            
             con.commit();
             con.close(); 
             pw.println("</table>"); 
             pw.println("</h3></center>");
             pw.println("</div>");
             pw.print("</body>");
             pw.print("</html>");
             
             
             
        }    
    catch(Exception exe){System.out.println("Exception caught");}
	}

}
