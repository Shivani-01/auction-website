import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import buzzfire.hm;
import buzzfire.objcall;
import buzzfire.repo;

/**
 * Servlet implementation class logbuy
 */

@WebServlet("/logbuy")

public class logbuy extends HttpServlet {
	int id=0;
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public logbuy() {
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
        
        System.out.println(a);
        System.out.println(b);
        System.out.println("***************1logbuy");
        
        HttpSession session = request.getSession();
        session.setAttribute("idbuy",a );
        
        String Uid="root";
        String password="root";
        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
        String query="select * from buylogin where id="+'"'+a+'"'+";";
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        try {
        	
        	Class.forName("com.mysql.jdbc.Driver");
			
		        Connection con= DriverManager.getConnection(DB_URL,Uid,password);
		        
	        Statement stmt=con.createStatement();
	        ResultSet rs=stmt.executeQuery(query);
	        rs.next();
	        String str=rs.getString("pass");
	        	        
	        stmt.close();
	        con.close();
	
	        if (str.equals(b)){
	        	
	        	RequestDispatcher rd=request.getRequestDispatcher("display.html");  
                rd.forward(request,response);

		    }
		    else{
		         System.out.println(b);
		         out.println("Wrong username or password");
		         RequestDispatcher rd=request.getRequestDispatcher("/logbuy.html");  
		         rd.include(request,response);
		    }
        }
        catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
		
	}

}
