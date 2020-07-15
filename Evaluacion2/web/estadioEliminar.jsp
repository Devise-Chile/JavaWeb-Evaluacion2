<%-- 
    Document   : estadioEliminar
    Created on : 14/07/2020, 08:00:44 PM
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
        <title>Eliminar Estadio</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        
        <form action="ControladorUsuario" method="post">
            <fieldset class="uk-fieldset">
                <div>
                <legend class="uk-legend">Modificar Estadio</legend>
                </div><br /><br />
                <div>
                    Código
                    <input class="uk-input uk-form-width-large" type="text" name="run" readonly="true" value="<%=  %>">
                </div><br />
                <div>
                    Nombre
                    <input class="uk-input uk-form-width-large" type="text" name="nombre" readonly="true" value="<%= ) %>">
                </div><br />
                <div>
                    Ciudad
                    <input class="uk-input uk-form-width-large" type="text" name="run" readonly="true" value="<%=  %>">
                </div><br />
                <div>
                    Capacidad
                    <input class="uk-input uk-form-width-large" type="text" name="run" readonly="true" value="<%=  %>">
                </div><br />
                
                <a class="uk-button uk-button-default" href="ciudades.jsp">Modificar</a>
            </fieldset>
            <input type="hidden" name="accion" value="3"/>
        </form>
                <a class="uk-button uk-button-default" href="ciudades.jsp">Cancelar</a>
    </body>
</html>
