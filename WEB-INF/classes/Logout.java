import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Logout extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			PrintWriter out=res.getWriter();
			Cookie[] ck=req.getCookies();
			out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
			out.println("<body><br></h1><br> <h3 align=center>Logged Out Succesfully</h3></body>");
			String s=ck[0].getValue();
			Connection con=null;
			PreparedStatement pst=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				pst=con.prepareStatement("select books,sum(cnt) from "+ck[0].getValue()+" group by books");
				pst.executeQuery();
				ResultSet rs=pst.getResultSet();
				int f=0;
				String s1="\n\nYour items are in the cart \n";
				while(rs.next()){
					 s1=s1+rs.getString(1)+"\t" +rs.getString(2)+"    items\n ";
				}			
				pst=con.prepareStatement("SELECT EMAIL FROM LOGIN WHERE USERNAME=?");
				pst.setString(1,s);
				pst.executeQuery();
				rs=pst.getResultSet();
				rs.next();
				String s2=rs.getString(1);
				Mailer.send(s2,"Please checkout! Your items are waiting in the cart", s1);
				Cookie ck1=new Cookie("username","");
				ck1.setMaxAge(0);
				res.addCookie(ck1);
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