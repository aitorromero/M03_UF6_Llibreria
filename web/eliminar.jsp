<%-- 
    Document   : eliminar
    Created on : 31-may-2017, 19:09:15
    Author     : ALUMNEDAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>   

        <%@ include file="myHeader.html" %>

        <form action="GestioLlibres?accio=eliminar" method="post">            

            <center><b>Eliminar llibre per ISBN:</b></center>
            <br><br>
            <table cellspacing="2" cellpadding="2" border="0" align="center">
                <tr>
                    <td align="right">ISBN:</td>
                    <td><input type="Text" name="isbn_" size="13"></td>
                </tr>                             

            </table>   

            <% String resposta = (String) request.getAttribute("eliminat");%>
             <a ><%=(resposta == null) ? "" : resposta%> </a>

        </form>
        
        <a href='index.jsp'>TORNAR</a><br><br>

    </body>
</html>
