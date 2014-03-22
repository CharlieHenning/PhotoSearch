<%-- 
    Document   : index
    Created on : Dec 6, 2013, 2:55:44 PM
    Author     : Owner
--%>
<%
   String name = request.getParameter( "creatorName" );
   session.setAttribute( "theName", name );
  // DataBaseAccess dba = new DataBaseAccess();
   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
        <script>
           function search(){
               var searchValue = document.getElementById("creatorName").value;
               
	            $.ajax({ 
	            url:'DBConnector', 
	            type:'POST', 
	            data:{searchValue:searchValue}, 
	            success:processResponse
	            
	            }); 
	        }
            function processResponse(newHTML){           
				document.getElementById("responseHTML").innerHTML = newHTML;
			} 
        </script>
    </head>
    <body>
        <FORM METHOD=POST >
Enter a Creator name:<INPUT TYPE=TEXT ID=creatorName NAME=creatorName SIZE=20><br/>

<P><INPUT TYPE=button onclick='search();'  value='Search!'>
        </form>
        <div id='responseHTML'></div>
        
        
    </body>
</html>
