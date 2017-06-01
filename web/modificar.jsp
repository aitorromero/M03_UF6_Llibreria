<%-- 
    Document   : modificar
    Created on : 31-may-2017, 19:08:52
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

        <form action="GestioLlibres?accio=afegir" method="post">            

            <center><b><h3>Llibre a modificar:</h3></b></center>
            
            <table cellspacing="2" cellpadding="2" border="0" align="center">
                <tr>
                    <td></td>
                    <td><h4>ISBN del llibre a modificar</h4></td>
                </tr>
                <!--<center><h4>ISBN del llibre a modificar</h4></center>-->
                <tr>
                    <td align="right">ISBN:</td>
                    <td><input type="Text" name="isbn_" size="13"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><h4>Dades que es volen modificar</h4></td>
                    <!--<td colspan="2" align="center"><h4>Dades que es volen modificar</h4></td>-->
                </tr>               
                
                <tr>
                    <td align="right">Títol:</td>
                    <td><input type="Text" name="titol_" size="30"></td>
                </tr>
                <tr>
                    <td align="right">Autor:</td>
                    <td><input type="Text" name="autor_" size="30"></td>
                </tr>
                <tr>
                    <td align="right">Editorial:</td>
                    <td><input type="Text" name="editorial_" size="20"></td> 
                </tr>               
                <tr>
                    <td align="right">Any edició:</td>
                    <td><input type="Text" name="anyEdicio_" size="4"></td>
                </tr>
                <tr>
                    <td align="right">Estoc:</td>
                    <td><input type="Text" name="estoc_" size="3"></td>
                </tr>
                <tr>
                   <td colspan="2" align="center"><input type="Submit" value="Modificar"></td>
                </tr>                

            </table>   

            <% String resposta = (String) request.getAttribute("modificat");%>
             <a ><%=(resposta == null) ? "" : resposta%> </a>

        </form>
        <br>
        <br>
        <center><a href='index.jsp'>TORNAR</a></center>

    </body>
</html>
