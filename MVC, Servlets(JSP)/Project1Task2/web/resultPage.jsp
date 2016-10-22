<%-- 
    Document   : firstpage
    Created on : Sep 15, 2016, 7:50:31 PM
    Author     : Samyak
view of the result page
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%= request.getAttribute("doctype") %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <h1> Distributed System Class Clicker</h1>
            <br>
            <%  if(Integer.parseInt(request.getAttribute("radioTag1").toString()) == 0 && Integer.parseInt(request.getAttribute("radioTag2").toString()) == 0 && Integer.parseInt(request.getAttribute("radioTag3").toString())== 0 &&Integer.parseInt(request.getAttribute("radioTag4").toString()) == 0)
                {
                    out.println("<h4>No result to display</h4>");
                }
                
                else
                {
                    if(Integer.parseInt(request.getAttribute("radioTag1").toString()) != 0)
                    {
                        out.println("<h4>A:"+ request.getAttribute("radioTag1")+ "</h4>");
                    
                    }
                    
                    if(Integer.parseInt(request.getAttribute("radioTag2").toString()) != 0)
                    {
                        out.println("<h4>A:"+ request.getAttribute("radioTag2")+ "</h4>");
                    
                    }
                    if(Integer.parseInt(request.getAttribute("radioTag3").toString()) != 0)
                    {
                        out.println("<h4>A:"+ request.getAttribute("radioTag3")+ "</h4>");
                    
                    }
                    if(Integer.parseInt(request.getAttribute("radioTag4").toString()) != 0)
                    {
                        out.println("<h4>A:"+ request.getAttribute("radioTag4")+ "</h4>");
                    
                    }
                        out.println("All results have been cleared");
                }%>
            
    </body>
</html>
