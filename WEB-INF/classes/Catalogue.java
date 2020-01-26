import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Catalogue extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			PrintWriter out=res.getWriter();
			Cookie[] ck=req.getCookies();
			out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
			out.println("<body ><h1> Welcome  " + ck[0].getValue() +"</h1> </body>");
			String s=ck[0].getValue();
			Connection con=null;
			PreparedStatement pst=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				pst=con.prepareStatement("select * from tab");
				pst.executeQuery();
				ResultSet rs=pst.getResultSet();
				int f=0;
				while(rs.next()){
					String s1=rs.getString(1);
					if(s.equals(s1.toLowerCase()))
							f=1;
				}
				if(f==0){
				pst=con.prepareStatement("CREATE TABLE "+s+"(books varchar2(40),cnt number(10))");
				pst.execute();
				}
			res.sendRedirect("order");
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