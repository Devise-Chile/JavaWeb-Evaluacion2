<%-- 
    Document   : Jugadores
    Created on : 13/07/2020, 09:40:20 PM
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
        <title>Jugadores</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <h3>Jugadores Registrados</h3>
        <table class="uk-table uk-table-divider">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Edad</th>
                    <th>Posición</th>
                    <th>Sueldo Anual</th>
                    <th>Equipo Actual</th>
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
                    <td></td>
                    <td><a href="modificarJugador.jsp?cod=<%=  %>">
                        <input type="button" value="Modificar"/>
                        </a>
                    </td>
                    <td><a href="eliminarJugador.jsp?id=<%=  %>">
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
