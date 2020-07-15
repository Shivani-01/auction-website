

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import buzzfire.objcall;
import buzzfire.repo;

/**
 * Servlet implementation class history
 */
@WebServlet("/history")
public class history extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public history() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
		 String Uid="root";
	        String password="root";
	        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
	       // String query="Select * from repocon;";
	        out.println("<html>");
		    out.println("<head>");
		    out.println("<h1>Bids you have won</h1>");
		   
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
				
			    Connection con= DriverManager.getConnection(DB_URL,Uid,password);
			    HttpSession session = request.getSession();
				 String id = (String) session.getAttribute("idbuy");
		        Statement stmt=con.createStatement();
		         ResultSet rs = stmt.executeQuery("SELECT * FROM reports where winner ="+'"'+id+'"'+";");
		        
		         while (rs.next()) {	            
		        	 String name = rs.getString("id");
		             String head = rs.getString("head");
		             String cont = rs.getString("cont");

		             session.setAttribute("idrepo",name );
		             out.println(name);
		             out.println(head);
		             out.println(cont);
		             
		             
		             //System.out.println(name+"    "+head);
		             out.println("<br>");
		             out.println("<br>");
		             
		         }
		         out.println(" <button onclick=\"window.location.href='rate.html'\">Report</button> " );
		     }
	        catch(Exception e)
			{
				System.out.println("HI I'm error of display");
				e.printStackTrace();
			}

}
}
