<%-- 
    Document   : registro
    Created on : 11/07/2020, 07:49:09 PM
    Author     : amaru
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
    <center>
        <h1>Registro de Usuarios</h1>
        <form action="ControladorUsuario" method="post">
            <table>
            <tr>
               <td>Run</td>
                <td><input type="text" name="run"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre"/></td>
            </tr>
            <tr>
                <td>Apellido</td>
                <td><input type="text" name="apellido"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Registrar"/></td>
                <td><a href="index.jsp">
                    <input type="button" value="Volver"/>
                    </a>
                </td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="2"/>
        </form>
        <% if(request.getParameter("msj")!= null){%>
        <h3><%= request.getParameter("msj") %></h3>
        <%}%>
    </center>
    </body>
</html>
