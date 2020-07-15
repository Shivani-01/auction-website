

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class fake
 */
@WebServlet("/fake")
public class fake extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fake() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Im in fake page");
		doGet(request, response);
		 String Uid="root";
	        String password="root";
	        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
	       // String query="Select * from repocon;";
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");

	             HttpSession session = request.getSession();
	             String id = (String) session.getAttribute("idrepo");
	             System.out.println(id);
			    Connection con= DriverManager.getConnection(DB_URL,Uid,password);
			        
		        Statement stmt=con.createStatement();
		         ResultSet rs = stmt.executeQuery("SELECT * FROM repologin where id="+'"'+id+'"'+";");
		        
		         while (rs.next()) {	            
		             int pt = rs.getInt("rate");
		             System.out.println("Rating prev is"+pt);
		             if(pt==5)
		             {
		            	 String query="delete from repologin where id='"+id+"' ;";
					        try {        
						        Statement stmt1=con.createStatement();
						        int rs1=stmt1.executeUpdate(query);
						        System.out.println("deleted");
						     System.out.println(rs);			      
						        
						        			        
						        System.out.println("done");
						        stmt1.close();
					        }		    
					        catch(Exception e)
					        {
					        	e.printStackTrace();
					        }
					        
		             }
		             else
		             {
		            	 
		            	 pt++;			
		            	 System.out.println(pt);
		            	 try {
		            		 Statement stmt2=con.createStatement();
				         int rs2 = stmt2.executeUpdate("update repologin set rate="+pt+ " where id="+'"'+id+'"'+";");
				         System.out.println("Rating updated");
				         stmt2.close();
		             }
		            	 catch(Exception e)
					        {
		            		 System.out.println("Rating update nii ho pa rhi");
					        	e.printStackTrace();
					        }
		             }
		            	 
		          }
		      
		        stmt.close();
		        con.close();
		        RequestDispatcher rd=request.getRequestDispatcher("bye2.html");  
                rd.forward(request,response);

		        
	        }
	        catch(Exception e)
			{
				System.out.println("HI I'm error of fake");
				e.printStackTrace();
			}
	}

}
