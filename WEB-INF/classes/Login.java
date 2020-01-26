import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Login extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			Connection con=null;
			PreparedStatement pst=null;
			PrintWriter out=res.getWriter();
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				pst=con.prepareStatement("SELECT USERNAME,PASSWORD,EMAIL from login");
				pst.executeQuery();
				ResultSet rs=pst.getResultSet();
				String un=req.getParameter("USERNAME");
				String pwd=req.getParameter("PASSWORD");
				int f=0;
				while(rs.next()){
					String s1=rs.getString(1);
					String s2=rs.getString(2);
					String s3=rs.getString(3);
					if(un.toLowerCase().equals(s1.toLowerCase())&&pwd.equals(s2)){
						f=1;  
						Mailer.send(s3,"LOGGED IN","Successfully logged in");
						break;  
						}
				}
				if(f==1){
					Cookie ck = new Cookie("username", un);
					res.addCookie(ck);
					
					res.sendRedirect("catalogue");
				}
				else{
					out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
					out.println("<body ><br> <br> <h1 align=center >  Please enter valid credentials </h1></body>");
						}
			}
			catch(SQLException | ClassNotFoundException se){
					out.println(se);
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