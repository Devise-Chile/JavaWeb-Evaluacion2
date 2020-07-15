<%-- 
    Document   : division
    Created on : 14/07/2020, 07:12:17 PM
    Author     : amaru
--%>

<%@page import="dao.DivisionDAO"%>
<%@page import="modelos.Division"%>
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
        <title>Divisiones</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <h3>Divisiones</h3>
            
            <form action="ControladorDivision" method="post">
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
                        ArrayList<Division> divisiones = new DivisionDAO().obtenerDivisiones();
                        for(Division d: divisiones) {
                    %>
                    <tr>
                        <td><%=d.getCodigo()%></td>
                        <td><%=d.getNombre()%></td>
                        <td><a href="divisionModificar.jsp?cod=<%= d.getCodigo()  %>">
                            <input type="button" value="Modificar"/>
                            </a>
                        </td>
                        <td><a href="divisionEliminar.jsp?id=<%= d.getNombre() %>">
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
