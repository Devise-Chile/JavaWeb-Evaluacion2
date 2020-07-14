<%-- 
    Document   : index
    Created on : 11/07/2020, 07:18:00 PM
    Author     : amaru
--%>

<%@page import="modelos.Usuario"%>
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


        <title>Inicio</title>
    </head>
    <body>
        <nav class="uk-navbar-container" uk-navbar>

            <div class="uk-navbar-right">

                <ul class="uk-navbar-nav">
                    <li><a href="registro.jsp">Registro usuario</a></li>
                </ul>
            </div>
        </nav>
        <div class="uk-container">
            <center>
                <br /><br />
                <form action="ControladorUsuario" method="post">
                    
                    <fieldset class="uk-fieldset">
                        <div>
                        <legend class="uk-legend">Inicio de Sesión</legend>
                        </div><br /><br />
                        <div>
                            Run
                            <input class="uk-input uk-form-width-large" type="text" name="run" value="">
                        </div><br />
                        <div>
                            Password
                            <input class="uk-input uk-form-width-large" type="password" name="password" value=""/>
                        </div><br />
                        <input type="submit" class="uk-button uk-button-default" value="Iniciar" />
                    </fieldset>
                    
                    <input type="hidden" name="accion" value="1"/>
                <% if(request.getParameter("msj")!= null){%>
                <h3><%= request.getParameter("msj") %></h3>
                <%}%>
                </form>
            </center>
        </div>
    </body>
</html>
