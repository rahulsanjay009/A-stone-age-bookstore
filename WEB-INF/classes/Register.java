import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Register extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			Connection con=null;
			PreparedStatement pst=null;
			PrintWriter out=res.getWriter();
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				pst=con.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?,?,?,?,?)");
				String un=req.getParameter("USERNAME");
				String pwd=req.getParameter("PASSWORD");
				String name=req.getParameter("NAME");
				String email=req.getParameter("EMAIL");
				String dob=req.getParameter("DOB");
				String skills[]=req.getParameterValues("SKILLS");
				String gender=req.getParameter("GENDER");
				String s="";		
				
				java.sql.Date db =java.sql.Date.valueOf(dob.toString());
				for(int i=0;i<skills.length;i++)
					s=s+skills[i]+"   ";
				pst.setString(1,un);
				pst.setString(2,pwd);
				pst.setString(3,name);
				pst.setString(4,email);
				pst.setDate(5,db);
				pst.setString(6,s);
				pst.setString(7,gender);
				int i=pst.executeUpdate();
				if(i>0){
					out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
					out.println("<body><br> <br> <h1 align=center >  Successfully registered!!!    Please Login....  </h1></body>");
					Mailer.send(email,"Welcome to ...!!!","Successfully Registered");
				}
				
			}
			catch(SQLException | ClassNotFoundException se){
					out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
					out.println("<body ><br> <br> <h1 align=center > Sorry not registerred... Please come again later....</h1></body>");
			}
		finally{
            try{
                pst.close();
				if(con!=null)
                con.close();
            }
            catch(SQLException se){
                out.println("<h1> Unable to continue pst close() error</h1>");
           }
		}
    }
}