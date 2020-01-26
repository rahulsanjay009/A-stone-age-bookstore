import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Checkout extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			PrintWriter out=res.getWriter();
			Cookie[] ck=req.getCookies();
			out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
			out.println("<body><h1> All items Checked out Mr. " + ck[0].getValue() +" !!!</h1></body>");
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
				String s1="\n\nYou have been succesfully checked out the following items\n";
				while(rs.next()){
					 s1=s1+rs.getString(1)+"\t" +rs.getString(2)+"    items\n ";
				}			
				pst=con.prepareStatement("SELECT EMAIL FROM LOGIN WHERE USERNAME=?");
				pst.setString(1,s);
				pst.executeQuery();
				rs=pst.getResultSet();
				rs.next();
				String s2=rs.getString(1);
				Mailer.send(s2,"Your order has been confirmed ", s1);
				pst=con.prepareStatement("DELETE FROM "+s);
				pst.execute();
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