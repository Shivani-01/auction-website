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

import buzzfire.objcall;
import buzzfire.repo;

/**
 * Servlet implementation class display
 */
@WebServlet("/display")
public class display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public display() {
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
	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<h1>News content</h1>");
	    String Uid="root";
        String password="root";
        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
       // String query="Select * from repocon;";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			
		    Connection con= DriverManager.getConnection(DB_URL,Uid,password);
		        
	        Statement stmt=con.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM repocon;");
	        
	         while (rs.next()) {	            
	             String name = rs.getString("id");
	             String head = rs.getString("head");
	             System.out.println(name+"    "+head);
	             
	             HttpSession session = request.getSession();
	             session.setAttribute("bidder",head );
	             System.out.println(head);
	             System.out.println("attribute set");
	             
	             out.println(name);
	             out.println(head);
	             
	             out.println(" <button onclick=\"window.location.href='bid.html'\">BID</button> " );
	             out.println("</div>");
	             
	          }
	      
	        stmt.close();
	        con.close();
	        
        }
        catch(Exception e)
		{
			System.out.println("HI I'm error of display");
			e.printStackTrace();
		}
	   /* objcall o=new objcall();
        
        o.print();
        System.out.println("//////////2");
        repo r;
        
	    
	    for (int i=0; i<objcall.l.size(); i++) 
            {System.out.println(objcall.l.get(i)+" ");
            out.println("<div>");
            r=objcall.l.get(i);
            out.println(r.getusername());
            out.println(r.geth());
          //  out.println(r.getc());
            out.println("<form action=bid.html>");
            out.println(" <button type=\"submit\">BID</button>" + 
            		
            		"  </div>" + 
            		"</form>" );
            out.println("</div>");
            }*/
	    
	    
	    out.println("</head>");
	    out.println("</html>");   
        }	
}
