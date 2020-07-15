<%@page import="modelos.Usuario"%>

<% 
    if(session.getAttribute("usuario")!= null){
        Usuario usuario = (Usuario) session.getAttribute("usuario");
    } else{
        response.sendRedirect("index.jsp?msj=acceso denegado");
    }
%>