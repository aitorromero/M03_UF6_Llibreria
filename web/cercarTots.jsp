<%-- 
    Document   : cercarTots
    Created on : 31-may-2017, 19:09:27
    Author     : ALUMNEDAM
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Llibre"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="myHeader.html" %>
        

       

            <center><b>Cerca de tots els llibres:</b></center>
            <br>
            <table cellspacing="2" cellpadding="2" border="2" align="center">
                <tr>
                    <td colspan="6" align="center"><h4>Dades del llibre</h4></td>
                </tr> 
                <tr>
                    <td align="right">ISBN</td>
                    <td align="right">Títol</td>
                    <td align="right">Autor</td>
                    <td align="right">Editorial</td>
                    <td align="right">Any edició</td>
                    <td align="right">Estoc</td>
                    
                </tr>
            <%
            List<Llibre> resposta = (List<Llibre>) request.getAttribute("llibres");
            for (Llibre l : resposta) {
            %>
                                          
                <tr>                    
                    <td><%=l.getIsbn()%>"></td>
                    <td><%=l.getTitol()%></td>
                    <td><%=l.getAutor()%></td>
                    <td><%=l.getEditorial()%></td>
                    <td><%=l.getAnyEdicio()%></td>
                    <td><%=l.getEstoc()%></td>
                </tr> 
             
            <%}%>
            </table>  
        <br>
        <br>
        <center><a href='index.jsp'>TORNAR</a></center>
</body>
</html>
