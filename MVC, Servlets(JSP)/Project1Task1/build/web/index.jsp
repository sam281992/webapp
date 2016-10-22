<%-- 
    Document   : index
    Created on : Sep 15, 2016, 10:38:22 PM
    Author     : samyak
This is the main index page, where the option for the hashing algorithms to choose is provided.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
            <h1> Hashing Values</h1>
            <form class= "hashing" action="ComputeHashes" method="GET">
                <input type="text" name="textbox">
                <br>
                <input type="radio" name="radiobutton" value = "MD5" checked>MD5
                <br>
                <input type="radio" name="radiobutton" value = "SHA-1">SHA-1
                <br>
                <button type="submit" name = "submitbutton "> Submit </button>
            </form>                
        </center>
    </body>
</html>
