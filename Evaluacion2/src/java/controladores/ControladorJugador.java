/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.EquipoDAO;
import dao.JugadorDAO;
import dao.PosicionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Equipo;
import modelos.Jugador;
import modelos.Posicion;

/**
 *
 * @author rodrigo
 */
@WebServlet(name = "ControladorJugador", urlPatterns = {"/ControladorJugador"})
public class ControladorJugador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "1":
                    registrar(request, response);
                    break;
                case "2":
                    modificar(request, response);
                    break;
                case "3":
                    eliminar(request, response);
                    break;
            }
        } else {
            response.sendRedirect("jugadores.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fechaNacimiento = request.getParameter("f_nacimiento").trim();
            int codPosicion = Integer.parseInt(request.getParameter("cod_posicion").trim());
            int sueldo = Integer.parseInt(request.getParameter("sueldo").trim());
            int codEquipo = Integer.parseInt(request.getParameter("cod_equipo").trim());

            if (nombre.equals("") || apellido.equals("") || fechaNacimiento.equals("") || codPosicion < 1 || sueldo < 1 || codEquipo < 1) {
                response.sendRedirect("jugadores.jsp?msj=valores erroneos");
            } else {
                PosicionDAO pd = new PosicionDAO();
                Posicion posicion = pd.obtenerPosicion(codPosicion);
                
                EquipoDAO eqd = new EquipoDAO();
                Equipo equipo = eqd.obtenerEquipo(codEquipo);
                
                if (posicion != null) {
                    if (equipo != null) {
                        JugadorDAO jd = new JugadorDAO();
                        Jugador nuevoJugador = new Jugador(nombre, apellido, fechaNacimiento, posicion, sueldo, equipo);

                        int respuesta = jd.registrar(nuevoJugador);
                        if (respuesta == 1) {
                            response.sendRedirect("jugadores.jsp?msj=Jugador registrado");
                        } else {
                            response.sendRedirect("jugadores.jsp?msj=Jugador no se pudo registrar");
                        }
                    } else {
                        response.sendRedirect("jugadores.jsp?msj=Equipo no existe");
                    }
                } else {
                    response.sendRedirect("jugadores.jsp?msj=Posición no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("jugadores.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id").trim());
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String fechaNacimiento = request.getParameter("f_nacimiento").trim();
            int codPosicion = Integer.parseInt(request.getParameter("cod_posicion").trim());
            int sueldo = Integer.parseInt(request.getParameter("sueldo").trim());
            int codEquipo = Integer.parseInt(request.getParameter("cod_equipo").trim());

            if (id < 1 || nombre.equals("") || apellido.equals("") || fechaNacimiento.equals("") || codPosicion < 1 || sueldo < 1 || codEquipo < 1) {
                response.sendRedirect("jugadorModificar.jsp?msj=valores erroneos");
            } else {
                PosicionDAO pd = new PosicionDAO();
                Posicion posicion = pd.obtenerPosicion(codPosicion);
                
                EquipoDAO eqd = new EquipoDAO();
                Equipo equipo = eqd.obtenerEquipo(codEquipo);
                
                if (posicion != null) {
                    if (equipo != null) {
                        JugadorDAO jd = new JugadorDAO();
                        Jugador jugador = new Jugador(id, nombre, apellido, fechaNacimiento, posicion, sueldo, equipo);

                        if (jd.obtenerJugador(jugador.getId()) == null) {
                            response.sendRedirect("jugadorModificar.jsp?msj=Id de jugador inexistente");
                        } else {
                            int respuesta = jd.modificar(jugador);
                            if (respuesta > 0) {
                                response.sendRedirect("jugadores.jsp?msj=Jugador modificado");
                            } else {
                                response.sendRedirect("jugadores.jsp?msj=Jugador no se pudo modificar");
                            }
                        }
                    } else {
                        response.sendRedirect("jugadores.jsp?msj=Equipo no existe");
                    }
                } else {
                    response.sendRedirect("jugadores.jsp?msj=Posición no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("jugadores.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id").trim());

            if (id < 1) {
                response.sendRedirect("jugadores.jsp?msj=valores erroneos");
            } else {
                JugadorDAO jd = new JugadorDAO();
                Jugador jugador = jd.obtenerJugador(id);
                if (jugador != null) {
                    int respuesta = jd.eliminar(jugador);
                    if (respuesta == 1) {
                        response.sendRedirect("jugadores.jsp?msj=Jugador eliminado");
                    } else {
                        response.sendRedirect("jugadores.jsp?msj=Jugador no se pudo eliminar");
                    }
                } else {
                    response.sendRedirect("jugadores.jsp?msj=Jugador no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("jugadores.jsp?msj=" + e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
