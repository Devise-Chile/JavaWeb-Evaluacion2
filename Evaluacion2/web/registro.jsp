<%-- 
    Document   : registro
    Created on : 11/07/2020, 07:49:09 PM
    Author     : amaru
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- UIkit CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/css/uikit.min.css" />

        <!-- UIkit JS -->
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/js/uikit.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/uikit@3.5.4/dist/js/uikit-icons.min.js"></script>
        <title>Registro</title>
    </head>
    <body>
    <center>
        <nav class="uk-navbar-container" uk-navbar>

            <div class="uk-navbar-right">

                <ul class="uk-navbar-nav">
                    <li><a href="index.jsp">Inicio</a></li>
                </ul>

            </div>
        </nav>
        <br /><br />
        <form action="ControladorUsuario" method="post">
            <fieldset class="uk-fieldset">
                <div>
                <legend class="uk-legend">Registro de Usuarios</legend>
                </div><br /><br />
                <div>
                    Run
                    <input class="uk-input uk-form-width-large" type="text" name="run" value="">
                </div><br />
                <div>
                    Nombre
                    <input class="uk-input uk-form-width-large" type="text" name="nombre" value="">
                </div><br />
                <div>
                    Apellido
                    <input class="uk-input uk-form-width-large" type="text" name="apellido" value="">
                </div><br />
                <div>
                    Password
                    <input class="uk-input uk-form-width-large" type="password" name="password" value=""/>
                </div><br />
                <input type="submit" class="uk-button uk-button-default" value="Registrar" />
            </fieldset>
            <input type="hidden" name="accion" value="2"/>
        </form>
        <% if(request.getParameter("msj")!= null){%>
        <h3><%= request.getParameter("msj") %></h3>
        <%}%>
    </center>
    </body>
</html>
