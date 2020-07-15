<%-- 
    Document   : equipoModificar
    Created on : 14/07/2020, 07:38:28 PM
    Author     : amaru
--%>

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
        <title>Modificar Equipo</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <% if(request.getParameter("cod")!=null){
                Equipo e = new EquipoDAO().obtenerEquipo(Integer.parseInt(request.getParameter("cod")));
            %>
            <form action="ControladorEquipo" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Modificar Equipo</legend>
                    </div><br /><br />
                    <div>
                        Código
                        <input class="uk-input uk-form-width-large" type="text" name="codigo" readonly="true" value="<%= e.getCodigo() %>">
                    </div><br />
                    <div>
                        Nombre
                        <input class="uk-input uk-form-width-large" type="text" name="nombre" value="<%= e.getNombre() %>">
                    </div><br />
                    <div>
                        Procedencia
                        <select name="cod_ciudad" class="uk-select uk-form-width-large">
                            <option value="0">Seleccione</option>
                            <% for(Ciudad ciudad: new CiudadDAO().obtenerCiudades()) { %>
                                <option value="<%= ciudad.getCodigo()%>" <% if(ciudad.getCodigo() == e.getCiudad().getCodigo()){ %>selected="selected"<%}%>><%= ciudad.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />
                    <div>
                        Estadio
                        <select name="cod_estadio" class="uk-select uk-form-width-large">
                            <option value="0">Seleccione</option>
                            <% for(Estadio estadio: new EstadioDAO().obtenerEstadios()) { %>
                                <option value="<%= estadio.getCodigo()%>" <% if(estadio.getCodigo() == e.getEstadio().getCodigo()){ %>selected="selected"<%}%>><%= estadio.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />
                    <div>
                        División
                        <select name="cod_division" class="uk-select uk-form-width-large">
                            <option value="0">Seleccione</option>
                            <% for(Division division: new DivisionDAO().obtenerDivisiones()) { %>
                                <option value="<%= division.getCodigo()%>" <% if(division.getCodigo() == e.getDivision().getCodigo()){ %>selected="selected"<%}%>><%= division.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />

                    <input type="submit" class="uk-button uk-button-default" value="Modificar" />
                    <a class="uk-button uk-button-default" href="equipos.jsp">Cancelar</a>
                </fieldset>
                <input type="hidden" name="accion" value="2"/>
            </form>
            <% } %>        
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%> 
        </center>
    </body>
</html>
