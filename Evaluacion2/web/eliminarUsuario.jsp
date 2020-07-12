<%-- 
    Document   : eliminarUsuario
    Created on : 11/07/2020, 07:56:55 PM
    Author     : amaru
--%>

<%@page import="util.UsuarioUtil"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuairo</title>
    </head>
    <body><center>
        <h1>Confirme los datos a eliminar</h1>
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
               <td>Run</td>
               <td><input type="text" name="run" readonly="true" value="<%= u.getRun() %>"/></td>
            </tr>
            <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombre" readonly="true" value="<%= u.getNombre() %>"/></td>
            </tr>
            <tr>
                <td>Apellido</td>
                <td><input type="text" name="apellido" readonly="true" value="<%= u.getApellido() %>"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password"readonly="true" name="password" value="<%= UsuarioUtil.createPassword() %>" /> 
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Eliminar"/></td>
                <td><a href="intranet.jsp">
                    <input type="button" value="Cancelar"/>
                    </a>
                </td>
            </tr>
            
        </table>
            <input type="hidden" name="accion" value="4"/>
        </form>
        </center>
    </body>
</html>
