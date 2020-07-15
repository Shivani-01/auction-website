
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rregister
 */
@WebServlet("/rregister")
public class rregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rregister() {
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

		String a=request.getParameter("uname");
        String b=request.getParameter("psw");
        
        System.out.println("***************1");
        
        String Uid="root";
        String password="root";
        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
        String query="insert into repologin values("+'"'+a+'"'+','+'"'+b+'"'+",0);";
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        try {
              	
        	Class.forName("com.mysql.jdbc.Driver");
			
		      Connection con= DriverManager.getConnection(DB_URL,Uid,password);
		        
	        Statement stmt=con.createStatement();
	        
	        try {
	        	int rs=stmt.executeUpdate(query);
		        System.out.println(rs);
		        
	        RequestDispatcher rd1=request.getRequestDispatcher("/login.html");  
            rd1.forward(request,response);
            

            }
	        catch(Exception e)
	        {
		        out.println("<script type=\"text/javascript\">");

	        	out.println("alert('Username already in use ');");
	        	out.println("</script>");
		         RequestDispatcher rd=request.getRequestDispatcher("/registration.html");  
		         rd.include(request,response);
	        }
	   
	        stmt.close();
	        con.close();
	        
	}
        catch(Exception e)
        {
        	e.printStackTrace();
        }

}


}
