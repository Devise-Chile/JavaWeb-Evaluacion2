<%-- 
    Document   : Jugadores
    Created on : 13/07/2020, 09:40:20 PM
    Author     : amaru
--%>

<%@page import="dao.JugadorDAO"%>
<%@page import="modelos.Jugador"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
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
        <title>Jugadores</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <h3>Jugadores</h3>
            
            <form action="ControladorJugador" method="post">
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <td>Apellido</td>
                        <td><input type="text" name="apellido"/></td>
                    </tr>
                    <tr>
                        <td>Fecha de Nacimiento</td>
                        <td><input type="date" name="f_nacimiento"/></td>
                    </tr>
                    <tr>
                        <td>Posición</td>
                        <td>
                            <select name="cod_posicion" class="uk-select">
                                <option value="0">Seleccione</option>
                                <%
                                    ArrayList<Posicion> posiciones = new PosicionDAO().obtenerPosiciones();
                                    for(Posicion p: posiciones) {
                                %>
                                <option value="<%= p.getCodigo() %>"><%= p.getNombre() %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Sueldo</td>
                        <td><input type="number" name="sueldo"/></td>
                    </tr>
                    <tr>
                        <td>Equipo</td>
                        <td>
                            <select name="cod_equipo" class="uk-select">
                                <option value="0">Seleccione</option>
                                <%
                                    ArrayList<Equipo> equipos = new EquipoDAO().obtenerEquipos();
                                    for(Equipo e: equipos) {
                                %>
                                <option value="<%= e.getCodigo() %>"><%= e.getNombre() %></option>
                                <% } %>
                            </select>
                        </td>
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
                    <%
                        ArrayList<Jugador> jugadores = new JugadorDAO().obtenerJugadores();
                        for(Jugador j: jugadores) {
                    %>
                    <tr>
                        <td><%= j.getId() %></td>
                        <td><%= j.getNombre() %></td>
                        <td><%= j.getApellido() %></td>
                        <td><%= j.getEdad() %></td>
                        <td><%= j.getPosicion().getNombre() %></td>
                        <td><%= j.getSueldo() %></td>
                        <td><%= j.getEquipo().getNombre() %></td>
                        <td><a href="jugadorModificar.jsp?id=<%= j.getId() %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                        <td><a href="jugadorEliminar.jsp?id=<%= j.getId() %>">
                                <input type="button" value="Eliminar"/>
                            </a>
                        </td>       
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% if(request.getParameter("msj")!= null){%>
            <h3><%= request.getParameter("msj") %></h3>
            <%}%>
        </center>
    </body>
</html>
