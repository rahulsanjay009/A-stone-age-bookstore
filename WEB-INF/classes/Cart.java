import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.sql.*;
import java.io.*;
import java.text.*;
public class Cart extends HttpServlet{
    public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
			Connection con=null;
			PreparedStatement pst=null;
			PreparedStatement pst1=null;
			PrintWriter out=res.getWriter();
			Cookie[] ck=req.getCookies();
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				pst=con.prepareStatement("INSERT INTO "+ck[0].getValue()+" values(?,?)");
					
				try{
				String b[]=req.getParameterValues("BN");
				out.println("<head><link rel=stylesheet type=text/css href=style.css></head>");
				out.println("<body>");
				if((ck.length%2)==0)	{
			out.println("<div style=float:right><form action=logout method=post><input type=submit value=logout> </form> </div>");
					}		
				out.println("<form action=checkout    method=post>");
				for(int i=0;i<b.length;i++){				
					String a=req.getParameter(b[i]);
					int n=Integer.parseInt(a);
				pst.setString(1,b[i]);
				pst.setInt(2,n);
				int c=pst.executeUpdate();
				
					}
				pst1=con.prepareStatement("select books,sum(cnt) from "+ck[0].getValue()+" group by books");
				pst1.executeQuery();
				ResultSet rs=null;
				try{
				 rs=pst1.getResultSet();
				 out.println("<table border=1 align=center width=100% height=70%><tr><th>BOOK  </th> <th>QUANTITY</th></tr> ");
				while(rs.next())
				    out.println("<tr><td align=center><h3> "+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td> <td align=left> added to cart</td></h3> ");
				
				out.println("<tr><td></td><td><input type=submit value=checkout></td></tr></table></form></body>");
				}//try
				catch(NullPointerException f){
						out.println("<head><link rel=stylesheet type=text/css href=style.css></head><body ><br><br><h1 align=center>Your cart is empty!!! </h1>");
					}//catch
				}//try
				catch(NullPointerException s){
				pst1=con.prepareStatement("select books,sum(cnt) from "+ck[0].getValue()+" group by books");
				pst1.executeQuery();
				ResultSet rs=pst1.getResultSet();
				if(rs.next()){
				 out.println("<table align=center width=100% height=100%><tr><th>BOOK  </th> <th>quantity</th></tr> ");
				do{
				    out.println("<tr><td align=center><h3> "+rs.getString(1)+"</td><td align=center>"+rs.getString(2)+"</td> <td align=left> added to cart</td></h3> ");						
						}while(rs.next());
						out.println("<tr><td></td><td><input type=submit value=checkout></td></tr></table></form></body>");
				}
				else
					out.println("<head><link rel=stylesheet type=text/css href=style.css></head><body><br><br><br><h1 align=center>Your cart is empty!!! </h1>");
				} //catch
				
				
				
			}//try
			
			catch(SQLException | ClassNotFoundException | NullPointerException se){
					out.println("<head><link rel=stylesheet type=text/css href=style.css></head><body ><br><br><br><h1 align=center>Please Login!!! </h1>");
			}//catch
		finally{
            try{
              
				if(con!=null)
                con.close();
            }
            catch(SQLException se){
                out.println("<h1> Unable to continue pst close() error</h1>"+se);
           }
		}
    }
}