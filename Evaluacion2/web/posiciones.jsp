<%-- 
    Document   : posiciones
    Created on : 14/07/2020, 07:12:56 PM
    Author     : amaru
--%>

<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page import="java.util.ArrayList"%>
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
        <title>Posiciones</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <center class="uk-container">
            <h3>Posiciones</h3>
            <form action="ControladorPosicion" method="post">
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <td><input type="reset" value="Limpiar"/></td>
                        <td><input type="submit" value="Agregar"/></td>
                        <input type="hidden" name="accion" value="1"/>
                    </tr>
                </table>
            </form>
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%>

            <table class="uk-table uk-table-divider">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Posicion> posiciones = new PosicionDAO().obtenerPosiciones();
                        for(Posicion pos: posiciones) {
                    %>
                    <tr>
                        <td><%= pos.getCodigo() %></td>
                        <td><%= pos.getNombre() %></td>
                        <td><a href="posicionModificar.jsp?cod=<%=pos.getCodigo() %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                        <td><a href="posicionEliminar.jsp?cod=<%=pos.getCodigo() %>">
                                <input type="button" value="Eliminar"/>
                            </a>
                        </td>       
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </center>
    </body>
</html>
