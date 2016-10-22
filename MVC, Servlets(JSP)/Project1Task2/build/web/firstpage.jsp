<%-- 
    Document   : firstpage
    Created on : Sep 15, 2016, 7:50:31 PM
    Author     : Samyak
view of the first page in clicker
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        
            <h1> Distributed System Class Clicker</h1>
            <br>
            <form class= "clicker" action="submit" method="POST">
                <%
                    
                    if(request.getAttribute("radioTag") != null)
                    { %>
                        <h2>Your <%= "\'"+ request.getAttribute("radioTag") + "\'"%> is registered</h2><br>
                  <% } else
                     {%>
                     <h2> Choose an option</h2>
                     <%}%> 
                  
                   
                <h3> submit the answer to the next question</h3>
                <br>
                <input type="radio" name="radiobutton" value = "A">A
                <br>
                <input type="radio" name="radiobutton" value = "B">B
                <br>
                <input type="radio" name="radiobutton" value = "C">C
                <br>
                <input type="radio" name="radiobutton" value = "D">D
                <br><br>
                <button type="submit" name = "submitbutton "> Submit </button>
            </form>                
        
    
</html>
