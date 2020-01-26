<html>
    <head>
        <link rel=stylesheet type=text/css href="style.css">
    </head>
    <body>
	<% 
	Cookie ck[]=request.getCookies();
	try{
	if((ck.length%2)==0)	{   %>
			<form action=logout method=post><p align=right><input type=submit value=logout></p> </form>  <br><br> 
			<h1>You are logged in as <% out.println(ck[0].getValue()); %> </h1>
		<%	}		
	else{
		    out.println("<table align=center> <br><br>  <h1> LOGIN </h1> <br><form action=login method=POST>");
  out.println(" <tr><td> <label for=UN>USERNAME</label></td><td > <input  type=text id=UN name=USERNAME> </td></tr>");
            
  out.println("<tr ><td > <label for=PWD>PASSWORD</label></td><td> <input type=password id=PWD name=PASSWORD> </td></tr>");
  out.println(" <tr ><td align=right> <button type=reset> RESET </button></td><td align=left><button type=submit> LOGIN </button></td></tr> </form> </table>");
		}
	}
	catch(Exception e){
			out.println("Loading....");
	}
	
	%>

            
    </body>
</html>