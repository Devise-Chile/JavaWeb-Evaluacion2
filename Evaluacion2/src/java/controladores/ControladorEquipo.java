/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.CiudadDAO;
import dao.EstadioDAO;
import dao.DivisionDAO;
import dao.EquipoDAO;
import dao.JugadorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Ciudad;
import modelos.Estadio;
import modelos.Division;
import modelos.Equipo;

/**
 *
 * @author rodrigo
 */
@WebServlet(name = "ControladorEquipo", urlPatterns = {"/ControladorEquipo"})
public class ControladorEquipo extends HttpServlet {

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
            response.sendRedirect("equipos.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String nombre = request.getParameter("nombre").trim();
            int codCiudad = Integer.parseInt(request.getParameter("cod_ciudad").trim());
            int codEstadio = Integer.parseInt(request.getParameter("cod_estadio").trim());
            int codDivision = Integer.parseInt(request.getParameter("cod_division").trim());

            if (nombre.equals("") || codCiudad < 1 || codEstadio < 1 || codDivision < 1) {
                response.sendRedirect("equipos.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad ciudad = cd.obtenerCiudad(codCiudad);
                
                EstadioDAO esd = new EstadioDAO();
                Estadio estadio = esd.obtenerEstadio(codEstadio);
                
                DivisionDAO dd = new DivisionDAO();
                Division division = dd.obtenerDivision(codDivision);
                
                if (ciudad != null) {
                    if (estadio != null) {
                        if (division != null) {
                            EquipoDAO eqd = new EquipoDAO();
                            Equipo nuevoEquipo = new Equipo(nombre, ciudad, estadio, division);

                            if (eqd.obtenerEquipo(nuevoEquipo.getNombre()) == null) {
                                int respuesta = eqd.registrar(nuevoEquipo);
                                if (respuesta == 1) {
                                    response.sendRedirect("equipos.jsp?msj=Equipo registrado");
                                } else {
                                    response.sendRedirect("equipos.jsp?msj=Equipo no se pudo registrar");
                                }
                            } else {
                                response.sendRedirect("equipos.jsp?msj=Equipo ya existe");
                            }
                        } else {
                            response.sendRedirect("equipos.jsp?msj=División no existe");
                        }
                    } else {
                        response.sendRedirect("equipos.jsp?msj=Estadio no existe");
                    }
                } else {
                    response.sendRedirect("equipos.jsp?msj=Ciudad no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("equipos.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();
            int codCiudad = Integer.parseInt(request.getParameter("cod_ciudad").trim());
            int codEstadio = Integer.parseInt(request.getParameter("cod_estadio").trim());
            int codDivision = Integer.parseInt(request.getParameter("cod_division").trim());

            if (codigo < 1 || nombre.equals("") || codCiudad < 1 || codEstadio < 1 || codDivision < 1) {
                response.sendRedirect("estadioModificar.jsp?msj=valores erroneos");
            } else {
                CiudadDAO cd = new CiudadDAO();
                Ciudad ciudad = cd.obtenerCiudad(codCiudad);
                
                EstadioDAO esd = new EstadioDAO();
                Estadio estadio = esd.obtenerEstadio(codEstadio);
                
                DivisionDAO dd = new DivisionDAO();
                Division division = dd.obtenerDivision(codDivision);

                if (ciudad != null) {
                    if (estadio != null) {
                        if (division != null) {
                            EquipoDAO eqd = new EquipoDAO();
                            Equipo e = new Equipo(codigo, nombre, ciudad, estadio, division);

                            if (eqd.obtenerEquipo(e.getCodigo()) == null) {
                                response.sendRedirect("equipoModificar.jsp?msj=Codigo de equipo inexistente");
                            } else {
                                int respuesta = eqd.modificar(e);
                                if (respuesta > 0) {
                                    response.sendRedirect("equipos.jsp?msj=Equipo modificado");
                                } else {
                                    response.sendRedirect("equipos.jsp?msj=Equipo no se pudo modificar");
                                }
                            }
                        } else {
                            response.sendRedirect("equipos.jsp?msj=División no existe");
                        }
                    } else {
                        response.sendRedirect("equipos.jsp?msj=Estadio no existe");
                    }
                } else {
                    response.sendRedirect("equipos.jsp?msj=Ciudad no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("equipos.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());

            if (codigo < 1) {
                response.sendRedirect("equipos.jsp?msj=valores erroneos");
            } else {
                EquipoDAO eqd = new EquipoDAO();
                Equipo equipo = eqd.obtenerEquipo(codigo);
                if (equipo != null) {
                    JugadorDAO jd = new JugadorDAO();
                    
                    if (jd.existeEquipo(equipo)) {
                        response.sendRedirect("equipos.jsp?msj=No se puede eliminar por tener jugadores con este equipo");
                    } else {
                        int respuesta = eqd.eliminar(equipo);
                        if (respuesta == 1) {
                            response.sendRedirect("equipos.jsp?msj=Equipo eliminado");
                        } else {
                            response.sendRedirect("equipos.jsp?msj=Equipo no se pudo eliminar");
                        }
                    }
                } else {
                    response.sendRedirect("equipos.jsp?msj=Equipo no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("equipos.jsp?msj=" + e.getMessage());
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
