<%-- 
    Document   : ciudadEliminar
    Created on : 14/07/2020, 07:55:29 PM
    Author     : amaru
--%>

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
        <title>Eliminar Ciudad</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center>
            <% if(request.getParameter("cod")!=null){
              Ciudad c = new CiudadDAO().obtenerCiudad(Integer.parseInt(request.getParameter("cod")));
            %>
            <form action="ControladorCiudad" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Eliminar Ciudad</legend>
                    </div><br /><br />
                    <div>
                        Código
                        <input class="uk-input uk-form-width-large" type="text" name="codigo" readonly="true" value="<%=c.getCodigo()  %>">
                    </div><br />
                    <div>
                        Nombre
                        <input class="uk-input uk-form-width-large" type="text" name="nombre" readonly="true" value="<%=c.getNombre() %>">
                    </div><br />

                    <input type="submit" class="uk-button uk-button-default" value="Eliminar" />
                    <a class="uk-button uk-button-default" href="ciudades.jsp">Cancelar</a>
                </fieldset>
                <input type="hidden" name="accion" value="3"/>
            </form>
            <%}%>
            <% if(request.getParameter("msj")!= null){%>
                <h4><%= request.getParameter("msj") %></h4>
            <%}%> 
        </center>
    </body>
</html>
