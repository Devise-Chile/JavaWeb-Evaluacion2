<%-- 
    Document   : jugadorModificar
    Created on : 14/07/2020, 07:40:23 PM
    Author     : amaru
--%>

<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
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
        <title>Eliminar Posicion</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <center class="uk-container">
            <h3>Eliminar Posicion</h3>
            <% if(request.getParameter("cod")!=null){
                Posicion pos = new PosicionDAO().obtenerPosicion(Integer.parseInt(request.getParameter("cod")));
            %>
            <form action="ControladorPosicion" method="post">
                <table>
                    <tr>
                        <td>Codigo</td>
                        <td><input type="text" name="codigo" readonly="true" value="<%= pos.getCodigo()%>" /></td>
                    </tr>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre" readonly="true" value="<%= pos.getNombre() %>"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Eliminar"/></td>
                        <input type="hidden" name="accion" value="3"/>
                    </tr>
                </table>
            </form>
            <% }%>
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%>
        </center>
    </body>
</html>
