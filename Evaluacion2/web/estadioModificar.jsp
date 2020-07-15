<%-- 
    Document   : estadioModificar
    Created on : 14/07/2020, 08:00:55 PM
    Author     : amaru
--%>

<%@page import="dao.CiudadDAO"%>
<%@page import="modelos.Ciudad"%>
<%@page import="modelos.Estadio"%>
<%@page import="dao.EstadioDAO"%>
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
        <title>Modificar Estadio</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <% if(request.getParameter("cod")!=null){
                Estadio e = new EstadioDAO().obtenerEstadio(Integer.parseInt(request.getParameter("cod")));
            %>
            <form action="ControladorEstadio" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Modificar Estadio</legend>
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
                        Ciudad
                        <select name="cod_ciudad" class="uk-select uk-form-width-large">
                            <option value="0">Seleccione</option>
                            <% for(Ciudad ciudad: new CiudadDAO().obtenerCiudades()) { %>
                                <option value="<%= ciudad.getCodigo()%>" <% if(ciudad.getCodigo() == e.getCiudad().getCodigo()){ %>selected="selected"<%}%>><%= ciudad.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />
                    <div>
                        Capacidad
                        <input class="uk-input uk-form-width-large" type="number" name="capacidad" value="<%= e.getCapacidad() %>">
                    </div><br />

                    <input type="submit" class="uk-button uk-button-default" value="Modificar" />
                    <a class="uk-button uk-button-default" href="estadios.jsp">Cancelar</a>
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
