package pizzabox;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.sql.*;
/**
 * Servlet implementation class PizzaList
 */
@WebServlet("/pizzas")
public class PizzaList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn =null; // Create connection object
		String database = "duncanb"; // Name of database
		String user = "duncanb"; // 
		String password = "frEgbart4";
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk/" + database;

		String docType =
		    "<!DOCTYPE HTML >";

		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String extra = request.getParameter("extra");
		out.println(docType +"<h1> WELCOME TO CHECKER'S </h1>");
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) {
		    System.err.println(e);
		}
		
		// connecting to database
		try{
		    conn = DriverManager.getConnection(url, user, password);
			
		}
		catch(SQLException se) {
		    System.err.println(se);
		}
		// Create select statement and execute it
		
		try{
		    String selectSQL = "select * from pizzas where toppings like '%" + extra + "%'";
		    Statement stmt = conn.createStatement();
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
		    // Retrieve the results
	    out.println("<table border=\"1\"><tr><th>Name</th><th>Toppings</th><th>Price</></tr>");
		    while(rs1.next()){
			out.println("<tr><td> "+ rs1.getString("name") + "</td>");
			out.println("<td>" + rs1.getString("toppings") + "</td>"); 	  
			out.println("<td> " + rs1.getFloat("price") + "</td>");
			out.println("</tr>");
			
		    }
		    out.println("</table>");
		    conn.close();
		} catch(SQLException se) {
		    System.err.println(se);
		}
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
