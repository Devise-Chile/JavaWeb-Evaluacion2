<%-- 
    Document   : divisionModificar
    Created on : 14/07/2020, 07:59:06 PM
    Author     : amaru
--%>

<%@page import="dao.DivisionDAO"%>
<%@page import="modelos.Division"%>
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
        <title>Eliminar Divisiones</title>
    </head>
    <body>
        <%@include file="partials/header.jsp" %>
        <%@include file="partials/autenticado.jsp" %>
        <center class="uk-container">
            <% if(request.getParameter("cod")!=null){
                Division d = new DivisionDAO().obtenerDivision(Integer.parseInt(request.getParameter("cod")));
            %>
            <form action="ControladorDivision" method="post">
                <fieldset class="uk-fieldset">
                    <div>
                    <legend class="uk-legend">Modificar Division</legend>
                    </div><br /><br />
                    <div>
                        Código
                        <input class="uk-input uk-form-width-large" type="text" name="codigo" readonly="true" value="<%=d.getCodigo()  %>">
                    </div><br />
                    <div>
                        Nombre
                        <input class="uk-input uk-form-width-large" type="text" name="nombre" value="<%=d.getNombre() %>">
                    </div><br />

                    <input type="submit" class="uk-button uk-button-default" value="Modificar" />
                    <a class="uk-button uk-button-default" href="divisiones.jsp">Cancelar</a>
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
