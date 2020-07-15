<%-- 
    Document   : estadios
    Created on : 14/07/2020, 07:12:40 PM
    Author     : amaru
--%>

<%@page import="modelos.Estadio"%>
<%@page import="dao.EstadioDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
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
        <title>Estadios</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <h3>Estadios</h3>
            
            <form action="ControladorEstadio" method="post">
                <table>
                    <tr>
                        <td>Nombre</td>
                        <td><input type="text" name="nombre"/></td>
                    </tr>
                    <tr>
                        <td>Ciudad</td>
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
                        <td>Capacidad</td>
                        <td><input type="number" name="capacidad"/></td>
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
                        <th>Ciudad</th>
                        <th>Capacidad</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Estadio> estadios = new EstadioDAO().obtenerEstadios();
                        for(Estadio e: estadios) {
                    %>
                    <tr>
                        <td><%= e.getCodigo() %></td>
                        <td><%= e.getNombre() %></td>
                        <td><%= e.getCiudad().getNombre() %></td>
                        <td><%= e.getCapacidad()%></td>
                        <td><a href="estadioModificar.jsp?cod=<%= e.getCodigo() %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                        <td><a href="estadioEliminar.jsp?cod=<%= e.getCodigo() %>">
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
