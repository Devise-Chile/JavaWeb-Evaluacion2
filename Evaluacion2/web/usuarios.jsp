<%-- 
    Document   : usuarios
    Created on : 13/07/2020, 11:39:29 PM
    Author     : amaru
--%>

<%@page import="util.UsuarioUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.UsuarioDAO"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <!-- UIkit CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/css/uikit.min.css" />

        <!-- UIkit JS -->
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/js/uikit.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/js/uikit-icons.min.js"></script>
        <title>Usuarios</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
    <center>
        
        <% if(session.getAttribute("usuario")!= null){
             Usuario u = (Usuario) session.getAttribute("usuario");
            %>
        <h3><%= u.getNombre()+" "+u.getApellido() %></h3>
        <%}else{response.sendRedirect("index.jsp?msj=acceso denegado");}%>
        <h3>Usuarios Registrados</h3>
        <table class="uk-table uk-table-divider">
            <thead>
                <tr>
                    <th>Run</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Apellido</th>
                    <th>Modificar</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <%  UsuarioDAO ud = new UsuarioDAO();
                    ArrayList<Usuario> usuarios = ud.obtenerUsuarios(); 
                for(Usuario u:usuarios){
                %>
                <tr>
                    <td><%= u.getRun() %></td>
                    <td><%= u.getNombre() %></td>
                    <td><%= u.getApellido() %></td>
                    <td><%= UsuarioUtil.createPassword() %></td>
                    <td><a href="usuarioModificar.jsp?id=<%= u.getRun() %>">
                        <input type="button" value="Modificar"/>
                        </a>
                    </td>
                    <td><a href="usuarioEliminar.jsp?id=<%= u.getRun() %>">
                            <input type="button" value="Eliminar"/>
                        </a>
                    </td>       
                </tr>

                <% } %>
            </tbody>
        </table>
        <% if(request.getParameter("msj")!= null){%>
        <h4><%= request.getParameter("msj") %></h4>
        <%}%>
      </center>
    </body>
</html>
