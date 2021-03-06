/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CiudadDAO;
import dao.EquipoDAO;
import dao.EstadioDAO;
import modelos.Estadio;
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
 * @author rodrigo
 */
@WebServlet(name = "ControladorEstadio", urlPatterns = {"/ControladorEstadio"})
public class ControladorEstadio extends HttpServlet {

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
            response.sendRedirect("estadios.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String nombre = request.getParameter("nombre").trim();
            int codCiudad = Integer.parseInt(request.getParameter("cod_ciudad").trim());
            int capacidad = Integer.parseInt(request.getParameter("capacidad").trim());

            if (nombre.equals("") || codCiudad < 1 || capacidad < 1) {
                response.sendRedirect("estadios.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad ciudad = cd.obtenerCiudad(codCiudad);
                
                if (ciudad != null) {
                    EstadioDAO ed = new EstadioDAO();
                    Estadio nuevoEstadio = new Estadio(nombre, ciudad, capacidad);
                    
                    if (ed.obtenerEstadio(nuevoEstadio.getNombre()) == null) {
                        int respuesta = ed.registrar(nuevoEstadio);
                        if (respuesta == 1) {
                            response.sendRedirect("estadios.jsp?msj=Estadio registrado");
                        } else {
                            response.sendRedirect("estadios.jsp?msj=Estadio no se pudo registrar");
                        }
                    } else {
                        response.sendRedirect("estadios.jsp?msj=Estadio ya existe");
                    }
                } else {
                    response.sendRedirect("estadios.jsp?msj=Ciudad no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("estadios.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            int codCiudad = Integer.parseInt(request.getParameter("cod_ciudad").trim());
            int capacidad = Integer.parseInt(request.getParameter("capacidad").trim());

            if (codigo < 1 || nombre.equals("") || codCiudad < 1 || capacidad < 1) {
                response.sendRedirect("estadioModificar.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad ciudad = cd.obtenerCiudad(codCiudad);

                if (ciudad != null) {
                    EstadioDAO ed = new EstadioDAO();
                    Estadio e = new Estadio(codigo, nombre, ciudad, capacidad);
                    
                    if (ed.obtenerEstadio(e.getCodigo()) == null) {
                        response.sendRedirect("estadioModificar.jsp?msj=Codigo de estadio inexistente");
                    } else {
                        int respuesta = ed.modificar(e);
                        if (respuesta > 0) {
                            response.sendRedirect("estadios.jsp?msj=Estadio modificado");
                        } else {
                            response.sendRedirect("estadios.jsp?msj=Estadio no se pudo modificar");
                        }
                    }
                } else {
                    response.sendRedirect("estadios.jsp?msj=Ciudad no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("estadios.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());

            if (codigo < 1) {
                response.sendRedirect("estadios.jsp?msj=valores erroneos");
            } else {
                EstadioDAO ed = new EstadioDAO();
                Estadio estadio = ed.obtenerEstadio(codigo);
                if (estadio != null) {
                    EquipoDAO eqd = new EquipoDAO();
                    
                    if (eqd.existeEstadio(estadio)) {
                        response.sendRedirect("estadios.jsp?msj=No se puede eliminar por tener equipos con este estadio");
                    } else {
                        int respuesta = ed.eliminar(estadio);
                        if (respuesta == 1) {
                            response.sendRedirect("estadios.jsp?msj=Estadio eliminado");
                        } else {
                            response.sendRedirect("estadios.jsp?msj=Estadio no se pudo eliminar");
                        }
                    }
                } else {
                    response.sendRedirect("estadios.jsp?msj=Estadio no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("estadios.jsp?msj=" + e.getMessage());
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
