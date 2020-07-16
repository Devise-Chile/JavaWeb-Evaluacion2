<%-- 
    Document   : equipoModificar
    Created on : 14/07/2020, 07:38:28 PM
    Author     : amaru
--%>

<%@page import="dao.JugadorDAO"%>
<%@page import="modelos.Jugador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Estadio"%>
<%@page import="dao.EstadioDAO"%>
<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
<%@page import="modelos.Division"%>
<%@page import="dao.DivisionDAO"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
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
        <title>Ver Equipo</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <% if(request.getParameter("cod")!=null){
                Equipo e = new EquipoDAO().obtenerEquipo(Integer.parseInt(request.getParameter("cod")));
            %>
            <h3>Plantel de <%= e.getNombre() %></h3>
            
            <table class="uk-table uk-table-divider">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Edad</th>
                        <th>Posición</th>
                        <th>Sueldo Anual</th>
                        <th>Modificar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Jugador> jugadores = new JugadorDAO().obtenerJugadoresEquipo(e);
                        for(Jugador j: jugadores) {
                    %>
                    <tr>
                        <td><%= j.getId() %></td>
                        <td><%= j.getNombre() %></td>
                        <td><%= j.getApellido() %></td>
                        <td><%= j.getEdad() %></td>
                        <td><%= j.getPosicion().getNombre() %></td>
                        <td><%= j.getSueldo() %></td>
                        <td><a href="jugadorModificar.jsp?id=<%= j.getId() %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            
            <% } %>        
            
            
        </center>
    </body>
</html>
