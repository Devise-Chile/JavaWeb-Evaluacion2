/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.JugadorDAO;
import dao.PosicionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Posicion;

/**
 *
 * @author rodrigo
 */
@WebServlet(name = "ControladorPosicion", urlPatterns = {"/ControladorPosicion"})
public class ControladorPosicion extends HttpServlet {

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
            response.sendRedirect("posiciones.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String nombre = request.getParameter("nombre").trim();

            if (nombre.equals("")) {
                response.sendRedirect("posiciones.jsp?msj=valores erroneos");
            } else {
                PosicionDAO pd = new PosicionDAO();
                Posicion pos = new Posicion(nombre);
                if (pd.obtenerPosicion(pos.getNombre()) == null) {
                    int respuesta = pd.registrar(pos);
                    if (respuesta == 1) {
                        response.sendRedirect("posiciones.jsp?msj=Posicion registrada");
                    } else {
                        response.sendRedirect("posiciones.jsp?msj=Posicion no se pudo registrar");
                    }
                } else {
                    response.sendRedirect("posiciones.jsp?msj=Posicion ya existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("posiciones.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1 || nombre.equals("")) {
                response.sendRedirect("posicionModificar.jsp?msj=valores erroneos");
            } else {
                PosicionDAO pd = new PosicionDAO();
                Posicion nuevaPos = new Posicion(codigo, nombre);

                if (pd.obtenerPosicion(nuevaPos.getCodigo()) == null) {
                    response.sendRedirect("posicionModificar.jsp?msj=Codigo de posicion inexistente");
                } else {
                    int respuesta = pd.modificar(nuevaPos);
                    if (respuesta > 0) {
                        response.sendRedirect("posiciones.jsp?msj=Posicion modificada");
                    } else {
                        response.sendRedirect("posiciones.jsp?msj=Posicion no se pudo modificar");
                    }
                }
            }
        } catch (Exception e) {
            response.sendRedirect("posiciones.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1) {
                response.sendRedirect("posiciones.jsp?msj=valores erroneos");
            } else {
                PosicionDAO pd = new PosicionDAO();
                Posicion pos = new Posicion(codigo, nombre);
                if (pd.obtenerPosicion(pos.getCodigo()) != null) {
                    JugadorDAO jd = new JugadorDAO();
                    
                    if (jd.existePosicion(pos)) {
                        response.sendRedirect("posiciones.jsp?msj=No se puede eliminar por tener jugadores con esta posicion");
                    } else {
                        int respuesta = pd.eliminar(pos);
                        if (respuesta == 1) {
                            response.sendRedirect("posiciones.jsp?msj=Posicion eliminada");
                        } else {
                            response.sendRedirect("posiciones.jsp?msj=Posicion no se pudo eliminar");
                        }
                    }
                } else {
                    response.sendRedirect("posiciones.jsp?msj=Posicion no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("posiciones.jsp?msj=" + e.getMessage());
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
