<%-- 
    Document   : intranet
    Created on : 13/07/2020, 09:32:40 PM
    Author     : amaru
--%>

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
        <title>Equipos</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <h3>Equipos Registrados</h3>
        <table class="uk-table uk-table-divider">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Procedencia</th>
                    <th>Estadio</th>
                    <th>División</th>
                    <th>Jugadores</th>
                    <th>Modificar</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><a href="modificarEduipo.jsp?cod=<%=  %>">
                        <input type="button" value="Modificar"/>
                        </a>
                    </td>
                    <td><a href="eliminarEquipo.jsp?id=<%=  %>">
                            <input type="button" value="Eliminar"/>
                        </a>
                    </td>       
                </tr>
            </tbody>
            <% if(request.getParameter("msj")!= null){%>
            <h3><%= request.getParameter("msj") %></h3>
            <%}%>
        </table>
    </body>
</html>
