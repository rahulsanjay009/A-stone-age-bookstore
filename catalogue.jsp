<html>
      <head>
	<link rel="stylesheet" type="text/css"  href="style.css">
	<style>
		h3{
		text-align:center;
		color:white;
		}
		table{
			border-collapse:collapse;	
		}
	</style>

      </head>
      <body>
         <% 
	Cookie ck[]=request.getCookies();
	try{
	if((ck.length%2)==0)	{%>
			 <form action=logout method=post ><p align=right><input type=submit value=logout></p> </form>
	
		<%	}		
	
	}
	catch(Exception e){
			out.println("Loading.....");
	}
	
	%>
	<br><br>
	<table border=1  width=100% height=70%>
	<form action=cart method=post>
	<tr>
		<th >Book</th>
		<th> Book Name</th>
		<th>price</th>
		<th>Add to cart</th>
	</tr>	
	<tr>
	      <td align=center><img src="1.jpg" ></h3></td>
	      <td><h3>Book :   XML Bible
			<br> Author:  Winston	
			<br> Publication:  Wiely</h3></td>
			<td><h3>$40.5</h3></td>
		    <td align=center><input type="checkbox" name="BN"  value="XML Bible" ><input type="text" name="XML Bible" size=2></td>
	</tr>
	<tr>
	
	<td align=center><img src="2.jpg" class=center></h3></td>
	      <td><h3>Book : AI  <br>
		Author : S.Russel <br>
		Publication : Princeton hall
 </h3></td>
	      <td><h3>$63</h3></td>
		
	      <td align=center> <input type="checkbox"  name="BN" value="AI"><input type="text" name="AI" size=2 > </td>	
		  
	</tr>
	<tr>
	
	<td align=center><img src="3.jpg" class=center></h3></td>
	      <td><h3>Book :Java 2 <br>
		Author : Watson <br>
		Publication : BPB
		publications</h3></td>
	      <td><h3>$63</h3></td>
		  
	      <td align=center><input type="checkbox" name="BN" value="Java2"><input type="text" name="Java2" size=2 > </td>	
	</tr>
	<tr>
		<td colspan=3></td>
		<td align=center><input type="submit"  value="Add to cart "></td>
	</tr>
	</form>
	</table>
      </body>
</html>
