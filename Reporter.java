
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import buzzfire.Threader;
import buzzfire.check;
import buzzfire.objcall;
import buzzfire.repo;
import buzzfire.runmethod;


/**
 * Servlet implementation class Reporter
 */


@WebServlet("/Reporter")
public class Reporter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reporter() {
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
		
		String head=request.getParameter("headline");
        String cont=request.getParameter("content");
        System.out.println("//////////");
        
        objcall o=new objcall();
        
        o.print();
        System.out.println("//////////2");
        repo r=o.getobj();
        r.seth(head);
        r.setc(cont);
        
        System.out.println("//////////3");
        System.out.println(r.getpass()+r.getusername());
        
        
        String a=r.getusername();
        System.out.println("//////////4");
        String Uid="root";
        String password="root";
        String DB_URL="jdbc:mysql://localhost:3306/buzzfire";
        
        
        String query="insert into repocon values ("+'"'+a+'"'+","+'"'+head+'"'+","+'"'+cont+'"'+",NUll,0);";
       
        try {
        		
        	Class.forName("com.mysql.jdbc.Driver");
			
		    Connection con= DriverManager.getConnection(DB_URL,Uid,password);
		        
	        Statement stmt=con.createStatement();
	        int rs=stmt.executeUpdate(query);
	       // int rs1=stmt.executeUpdate(query1);
	        
	        
	     System.out.println(rs);
	    // System.out.println(rs1);
	      
	        stmt.close();
	        con.close();
	        
	        RequestDispatcher rd=request.getRequestDispatcher("sucess.html"); 	
	        rd.forward(request,response);
	    	runmethod t=new runmethod();
			 Thread f=new Thread(t);
			 check t1=new check();
			 Thread f1=new Thread(t1);
			 f.start();
			 System.out.println(Thread.currentThread().getName());
			 f1.start();
			 System.out.println(Thread.currentThread().getName());
	        try
	        {
	        System.out.println("I'm sleeping");
	        System.out.println(Thread.currentThread().getName());
			Thread.sleep(60000);
			Threader.main(null);
			 
			 
	        }
	        catch(Exception e)
			{
				System.out.println("HI I'm error of thread");
				e.printStackTrace();
			}
	    
	        /*try {
				Thread.sleep(60000);
				
		        System.out.println("//////////1");
		        o.print();
		        System.out.println("//////////2");
		       //  r=o.getobj();
		        runmethod t=new runmethod();
				t.remove();
			}
			*/
	        
	        
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
