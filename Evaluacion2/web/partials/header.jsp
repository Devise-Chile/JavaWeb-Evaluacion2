<nav class="uk-navbar-container" uk-navbar style="margin-bottom: 20px">
    <div class="uk-navbar-center">
        <ul class="uk-navbar-nav">
            <li class="uk-nav-header ">Gestor de</li>
            <li><a href="equipos.jsp">Equipos</a></li>
            <li><a href="jugadores.jsp">Jugadores</a></li>
            <li><a href="estadios.jsp">Estadios</a></li>
            <li><a href="divisiones.jsp">Divisiones</a></li>
            <li><a href="ciudades.jsp">Ciudades</a></li>
            <li><a href="posiciones.jsp">Posiciones</a></li>
            <li><a href="usuarios.jsp">Usuarios</a></li>
            <% if(session.getAttribute("usuario") != null) { %>
            <li><a href="Salir.jsp">Cerrar Sesión</a></li>
            <% } %>
        </ul>
    </div>
</nav>
