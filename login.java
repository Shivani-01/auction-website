import java.io.*;
import java.sql.*;
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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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

        HttpSession session = request.getSession();
        session.setAttribute("idrepo",a );
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
	System.out.println(a+" "+b);
	        if (str.equals(b)){
	        	System.out.println("true");
	        	repo r=new repo();
                r.setusername(a);
                r.setpass(b);
                
                objcall o=new objcall();
                
                o.setobj(r);
                
                o.print();
                
	        	RequestDispatcher rd=request.getRequestDispatcher("reporter.html");  
                rd.forward(request,response);
                
                System.out.println(r.getpass()+" "+r.getusername());

		    }
		    else{
		         System.out.println(b);
		         out.println("Wrong username or password");
		         RequestDispatcher rd=request.getRequestDispatcher("/login.html");  
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
