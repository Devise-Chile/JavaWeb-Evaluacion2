<%-- 
    Document   : intranet
    Created on : 13/07/2020, 09:32:40 PM
    Author     : amaru
--%>

<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
<%@page import="dao.EstadioDAO"%>
<%@page import="modelos.Estadio"%>
<%@page import="dao.DivisionDAO"%>
<%@page import="modelos.Division"%>
<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
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
        <title>Equipos</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <h3>Equipos</h3>
            
            <form action="ControladorEquipo" method="post">
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <td>Procedencia</td>
                        <td>
                            <select name="cod_ciudad" class="uk-select">
                                <option value="0">Seleccione</option>
                                <%
                                    ArrayList<Ciudad> ciudades = new CiudadDAO().obtenerCiudades();
                                    for(Ciudad c: ciudades) {
                                %>
                                <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Estadio</td>
                        <td>
                            <select name="cod_estadio" class="uk-select">
                                <option value="0">Seleccione</option>
                                <%
                                    ArrayList<Estadio> estadios = new EstadioDAO().obtenerEstadios();
                                    for(Estadio e: estadios) {
                                %>
                                <option value="<%= e.getCodigo() %>"><%= e.getNombre() %></option>
                                <% } %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>División</td>
                        <td>
                            <select name="cod_division" class="uk-select">
                                <option value="0">Seleccione</option>
                                <%
                                    ArrayList<Division> divisiones = new DivisionDAO().obtenerDivisiones();
                                    for(Division d: divisiones) {
                                %>
                                <option value="<%= d.getCodigo() %>"><%= d.getNombre() %></option>
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
                    <%
                        ArrayList<Equipo> equipos = new EquipoDAO().obtenerEquipos();
                        for(Equipo e: equipos) {
                    %>
                    <tr>
                        <td><%=e.getCodigo() %></td>
                        <td><%=e.getNombre() %></td>
                        <td><%=e.getCiudad().getNombre() %></td>
                        <td><%=e.getEstadio().getNombre() %></td>
                        <td><%=e.getDivision().getNombre() %></td>
                        <td>
                            <a href="equipoVer.jsp?cod=<%=e.getCodigo() %>">
                                <input type="button" value="Ver"/>
                            </a>
                        </td>
                        <td><a href="equipoModificar.jsp?cod=<%=e.getCodigo() %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                        <td><a href="equipoEliminar.jsp?cod=<%=e.getCodigo() %>">
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
