<%-- 
    Document   : posicionEliminar
    Created on : 14/07/2020, 08:02:12 PM
    Author     : amaru
--%>
<%@page import="dao.PosicionDAO"%>
<%@page import="modelos.Posicion"%>
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
        <title>Eliminar Posición</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <% if(request.getParameter("cod")!=null){
            Posicion pos = new PosicionDAO().obtenerPosicion(Integer.parseInt(request.getParameter("cod")));
        %>
        <center>
            <form action="ControladorPosicion" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Eliminar Posición</legend>
                    </div><br /><br />
                    <div>
                        Código
                        <input class="uk-input uk-form-width-large" type="text" name="codigo" readonly="true" value="<%= pos.getCodigo()%>">
                    </div><br />
                    <div>
                        Nombre
                        <input class="uk-input uk-form-width-large" type="text" name="nombre" readonly="true" value="<%= pos.getNombre() %>">
                    </div><br />

                    <input class="uk-button uk-button-default" type="submit" value="Eliminar" />
                    <a class="uk-button uk-button-default" href="posiciones.jsp">Cancelar</a>
                </fieldset>
                <input type="hidden" name="accion" value="3"/>
            </form>
            <% }%>
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%>
        </center>
    </body>
</html>
