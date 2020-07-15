

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class biddone
 */
@WebServlet("/biddone")
public class biddone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public biddone() {
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
		System.out.println("Im in biffi");
		HttpSession session = request.getSession();
		 String head = (String) session.getAttribute("bidder");
		 System.out.println(head);
		    int a=Integer.parseInt(request.getParameter("bid"));
		    String Uid="root";
	        String password="root";
	        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
	       System.out.println("Im biddone ");
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
				
			    Connection con= DriverManager.getConnection(DB_URL,Uid,password);
			        
		        Statement stmt=con.createStatement();
		         ResultSet rs = stmt.executeQuery("SELECT * FROM repocon where head="+'"'+head+'"'+";");
		         	        while(rs.next()) {    
		             int bid = rs.getInt("bid");
		          
		             System.out.println(bid+" "+a);
		             if(bid<a)
		             {
		            	try { 
		        		 String id = (String) session.getAttribute("idbuy");
		        		System.out.println(id);
				        Statement stmt1=con.createStatement();
				        Statement stmt2=con.createStatement();

				         int rs1 = stmt1.executeUpdate("update repocon set bidder="+'"'+id+'"'+" , bid="+a+ " where head="+'"'+head+'"'+";");
				         int rs2 = stmt2.executeUpdate("update reports set winner="+'"'+id+'"'+ "where head="+'"'+head+'"'+";");
				         System.out.println(rs1+" "+rs2);
				         System.out.println("updated");
		            	}
		            	catch(Exception e)
		            	{
		    				System.out.println("HI I'm error of updation of bidder");
		    				e.printStackTrace();
		    			}
		             }
		         	        }
		        System.out.println("working till here");
		        RequestDispatcher rd=request.getRequestDispatcher("bye.html"); 	
		        rd.forward(request,response);

//		        stmt.close();
	//	        con.close();
		        
	        }
	        catch(Exception e)
			{
				System.out.println("HI I'm error of biddone");
				e.printStackTrace();
			}

	}

}
