<%-- 
    Document   : index
    Created on : 11/07/2020, 07:18:00 PM
    Author     : amaru
--%>

<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body>
        <center>
        <h1>Inicio de Sesion</h1>
        <%  Cookie[] cookies = request.getCookies();
        String saludo ="";
        String run = "";
        String pass ="";
        Usuario usuario;
        for(int i=0; i<cookies.length; i++)
        {
        Cookie cookieActual = cookies[i];
        if(cookieActual.getName().equals("nombre"))
        {
        saludo += "Bienvenido "+cookieActual.getValue();
        }
        if(cookieActual.getName().equals("run")){
            run = cookieActual.getValue();
        }
        if(cookieActual.getName().equals("pass")){
            pass = cookieActual.getValue();
        }
        if(cookieActual.getName().equals("usuario")){
            String[] user = cookieActual.getValue().split(",");
            usuario = new Usuario(user[0],user[1],user[2],user[3]);
        }
            
        }
        %>
        <h3><%= saludo %></h3>
        <form action="ControladorUsuario" method="post">
        <table>
            <tr>
                <td>Id</td>
                <td><input type="text" name="run" value="<%= run %>"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" value="<%= pass %>"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Iniciar Sesion"/></td>
                <td><a href="registro.jsp">
                        <input type="button" value="Registrar"/>
                    </a>
                </td>
            </tr>
        </table>
            <input type="hidden" name="accion" value="1"/>
        <% if(request.getParameter("msj")!= null){%>
        <h3><%= request.getParameter("msj") %></h3>
        <%}%>
        </form>
        </center>
    </body>
</html>
