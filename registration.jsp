<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body >
	      <% 
	Cookie ck[]=request.getCookies();
	try{
	if((ck.length%2)==0)	{%>
			 <form action=logout method=post ><p align=right><input type=submit value=logout></p> </form><br><br>
			<h1>You are logged in as <%out.println(ck[0].getValue()); %></h1>
		<%	}		
	else{  %>
	<form action="register" method=get>
        <table align=center>

            <br>
			<tr >
                <td> <label for="un">USERNAME</label></td> 
                
                <td > <input  type="text" id="un" name="USERNAME" > </td> 
            </tr>
			<tr >
                <td> <label for="p">PASSWORD</label></td> 
                
                <td > <input  type="password" id="p" name="PASSWORD" > </td> 
            </tr>
            <tr >
                <td> <label for="n">NAME</label></td> 
                
                <td > <input  type="text" id="n" name="NAME"> </td> 
            </tr>
            
            <tr >
                <td > <label for="em">EMAIL</label></td>
                
                <td> <input type=text id="em" name="EMAIL"> </td>
            </tr>
            <tr> <td>DATE OF BIRTH</td><td><input type=date name="DOB"></td> </tr>
            <tr> <td>SKILLS</td></tr>
            <tr><td align=center><label for="c"><input type=checkbox id=c value="C" name="SKILLS"> C </label></td>
                <td align=center><label for="c_"><input type=checkbox id=c_ value="C++" name="SKILLS"> C++ </label></td>
                <td align=center><label for="j"><input type=checkbox id=j  value="Java" name="SKILLS"> Java </label></td>
            </tr>
            <tr><td>GENDER </td></tr>
            <tr>
                <td align=center> <label for="m"><input type=radio id=m value="Male" name="GENDER"> male </label></td>
                <td align=center> <label for="f"><input type=radio id=f value="Female" name="GENDER"> female </label></td>
                <td align=center> <label for="o"><input type=radio id=o value="others" name="GENDER"> others </label></td>
            </tr>
            <tr >
                <td></td><td align=left> <button type=submit> REGISTER </button></td>
            </tr>
        </table>
		</form>
		<%  }  
	}
	catch(Exception e){
			out.println("exception man!!!");
	}
	
	%>
	 
    </body>
</html>