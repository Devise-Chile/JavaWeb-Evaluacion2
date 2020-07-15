/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CiudadDAO;
import dao.EquipoDAO;
import dao.EstadioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Ciudad;

/**
 *
 * @author amaru
 */
@WebServlet(name = "ControladorCiudad", urlPatterns = {"/ControladorCiudad"})
public class ControladorCiudad extends HttpServlet {

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
            response.sendRedirect("ciudades.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String nombre = request.getParameter("nombre").trim();

            if (nombre.equals("")) {
                response.sendRedirect("ciudades.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad c = new Ciudad(nombre);
                if (cd.obtenerCiudad(c.getNombre()) == null) {
                    int respuesta = cd.registrar(c);
                    if (respuesta == 1) {
                        response.sendRedirect("ciudades.jsp?msj=Ciudad registrada");
                    } else {
                        response.sendRedirect("ciudades.jsp?msj=Ciudad no se pudo registrar");
                    }
                } else {
                    response.sendRedirect("ciudades.jsp?msj=Ciudad ya existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("ciudades.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1 || nombre.equals("")) {
                response.sendRedirect("ciudadModificar.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad nuevaCiudad = new Ciudad(codigo, nombre);

                if (cd.obtenerCiudad(nuevaCiudad.getCodigo()) == null) {
                    response.sendRedirect("ciudadModificar.jsp?msj=Codigo de ciudad inexistente");
                } else {
                    int respuesta = cd.modificar(nuevaCiudad);
                    if (respuesta > 0) {
                        response.sendRedirect("ciudades.jsp?msj=Ciudad modificada");
                    } else {
                        response.sendRedirect("ciudades.jsp?msj=Ciudad no se pudo modificar");
                    }
                }
            }
        } catch (Exception e) {
            response.sendRedirect("ciudades.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1) {
                response.sendRedirect("ciudades.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad ciudad = new Ciudad(codigo, nombre);
                if (cd.obtenerCiudad(ciudad.getCodigo()) != null) {
                    EstadioDAO esd = new EstadioDAO();
                    EquipoDAO eqd = new EquipoDAO();
                    
                    if (eqd.existeCiudad(ciudad)) {
                        response.sendRedirect("ciudades.jsp?msj=No se puede eliminar por tener equipos con esta ciudad");
                    } else if (esd.existeCiudad(ciudad)) {
                        response.sendRedirect("ciudades.jsp?msj=No se puede eliminar por tener estadios con esta ciudad");
                    } else {
                        int respuesta = cd.eliminar(ciudad);
                        if (respuesta == 1) {
                            response.sendRedirect("ciudades.jsp?msj=Ciudad eliminada");
                        } else {
                            response.sendRedirect("ciudades.jsp?msj=Ciudad no se pudo eliminar");
                        }
                    }
                } else {
                    response.sendRedirect("ciudades.jsp?msj=Ciudad no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("ciudades.jsp?msj=" + e.getMessage());
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
