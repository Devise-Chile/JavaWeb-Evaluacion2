<%-- 
    Document   : modificarUsuario
    Created on : 11/07/2020, 07:54:12 PM
    Author     : amaru
--%>

<%@page import="dao.UsuarioDAO"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Usuario</title>
    </head>
    <body>
    <% if(session.getAttribute("usuario")== null){
             response.sendRedirect("index.jsp?msj=acceso denegado");}%>
    <center>
        <h1>Modificacion de usuario</h1>
        <% Usuario u = new Usuario(); 
          if(request.getParameter("run")!= null){
              UsuarioDAO ud = new UsuarioDAO();
             if(ud.obtenerUsuario(request.getParameter("run"))!=null){
                 u = ud.obtenerUsuario(request.getParameter("run"));
             }
          }
        %>
        <form action="ControladorUsuario" method="post">
            <table>
            <tr>
               <td>ID</td>
               <td><input type="text" name="run" readonly="true" value="<%= u.getRun() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" value="<%= u.getNombre() %>"/></td>
            </tr>
            <tr>
                <td>Apellido</td>
                <td><input type="text" name="apellido" value="<%= u.getApellido() %>"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /> 
                    <div style="font-size: 8px;">para conservar password dejar vacio</div>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Modificar"/></td>
                <td><a href="intranet.jsp">
                    <input type="button" value="Volver"/>
                    </a>
                </td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="3"/>
        </form>
        </center>
    </body>
</html>
