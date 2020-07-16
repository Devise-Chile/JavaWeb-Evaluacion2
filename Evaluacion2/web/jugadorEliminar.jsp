<%-- 
    Document   : jugadorEliminar
    Created on : 14/07/2020, 07:40:13 PM
    Author     : amaru
--%>

<%@page import="dao.EquipoDAO"%>
<%@page import="modelos.Equipo"%>
<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
<%@page import="dao.JugadorDAO"%>
<%@page import="modelos.Jugador"%>
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
        <title>Eliminar Jugador</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <% if(request.getParameter("id")!=null){
                Jugador j = new JugadorDAO().obtenerJugador(Integer.parseInt(request.getParameter("id")));
            %>
            <form action="ControladorJugador" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Eliminar Jugador</legend>
                    </div><br /><br />
                    <div>
                        ID
                        <input class="uk-input uk-form-width-large" type="text" name="id" readonly="true" value="<%= j.getId()%>">
                    </div><br />
                    <div>
                        Nombre
                        <input class="uk-input uk-form-width-large" type="text" name="nombre" readonly="true" value="<%= j.getNombre() %>">
                    </div><br />
                    <div>
                        Apellido
                        <input class="uk-input uk-form-width-large" type="text" name="apellido" readonly="true" value="<%= j.getApellido() %>">
                    </div><br />
                    <div>
                        Fecha de Nacimiento
                        <input class="uk-input uk-form-width-large" type="date" name="f_nacimiento" readonly="true" value="<%= j.getFechaNacimiento() %>"/>
                    </div><br />
                    <div>
                        Posición
                        <select name="cod_posicion" class="uk-select uk-form-width-large">
                            <option value="0" disabled="disabled">Seleccione</option>
                            <% for(Posicion pos: new PosicionDAO().obtenerPosiciones()) { %>
                                <option value="<%= pos.getCodigo()%>" <% if(pos.getCodigo() == j.getPosicion().getCodigo()){ %>selected="selected"<%}else{%>disabled="disabled"<%}%>><%= pos.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />
                    <div>
                        Sueldo
                        <input class="uk-input uk-form-width-large" type="number" name="sueldo" readonly="true" value="<%= j.getSueldo() %>">
                    </div><br />
                    <div>
                        Equipo
                        <select name="cod_equipo" class="uk-select uk-form-width-large">
                            <option value="0" disabled="disabled">Seleccione</option>
                            <% for(Equipo e: new EquipoDAO().obtenerEquipos()) { %>
                                <option value="<%= e.getCodigo()%>" <% if(e.getCodigo() == j.getEquipo().getCodigo()){ %>selected="selected"<%}else{%>disabled="disabled"<%}%>><%= e.getNombre() %></option>
                            <% } %>
                        </select>
                    </div><br />
                    
                    <input type="submit" class="uk-button uk-button-default" value="Eliminar" />
                    <a class="uk-button uk-button-default" href="jugadores.jsp">Cancelar</a>
                </fieldset>
                <input type="hidden" name="accion" value="3"/>
            </form>
            <% } %>        
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%> 
        </center>
    </body>
</html>
