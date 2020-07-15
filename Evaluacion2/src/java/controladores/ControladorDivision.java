/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.DivisionDAO;
import dao.EquipoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Division;

/**
 *
 * @author rodrigo
 */
@WebServlet(name = "ControladorDivision", urlPatterns = {"/ControladorDivision"})
public class ControladorDivision extends HttpServlet {

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
            response.sendRedirect("divisiones.jsp?msj=acceso no permitido");
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String nombre = request.getParameter("nombre").trim();

            if (nombre.equals("")) {
                response.sendRedirect("divisiones.jsp?msj=valores erroneos");
            } else {
                DivisionDAO dd = new DivisionDAO();
                Division d = new Division(nombre);
                if (dd.obtenerDivision(d.getNombre()) == null) {
                    int respuesta = dd.registrar(d);
                    if (respuesta == 1) {
                        response.sendRedirect("divisiones.jsp?msj=Divisionregistrada");
                    } else {
                        response.sendRedirect("divisiones.jsp?msj=Division no se pudo registrar");
                    }
                } else {
                    response.sendRedirect("divisiones.jsp?msj=Division ya existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("divisiones.jsp?msj=" + e.getMessage());
        }
    }

    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1 || nombre.equals("")) {
                response.sendRedirect("divisionModificar.jsp?msj=valores erroneos");
            } else {
                DivisionDAO dd = new DivisionDAO();
                Division nuevaDivision = new Division(codigo, nombre);

                if (dd.obtenerDivision(nuevaDivision.getCodigo()) == null) {
                    response.sendRedirect("divisionModificar.jsp?msj=Codigo de division inexistente");
                } else {
                    int respuesta = dd.modificar(nuevaDivision);
                    if (respuesta > 0) {
                        response.sendRedirect("divisiones.jsp?msj=Division modificada");
                    } else {
                        response.sendRedirect("divisiones.jsp?msj=Division no se pudo modificar");
                    }
                }
            }
        } catch (Exception e) {
            response.sendRedirect("divisiones.jsp?msj=" + e.getMessage());
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int codigo = Integer.parseInt(request.getParameter("codigo").trim());
            String nombre = request.getParameter("nombre").trim();

            if (codigo < 1) {
                response.sendRedirect("divisiones.jsp?msj=valores erroneos");
            } else {
                DivisionDAO dd = new DivisionDAO();
                Division division = new Division(codigo, nombre);
                if (dd.obtenerDivision(division.getCodigo()) != null) {
                    EquipoDAO eqd = new EquipoDAO();
                    
                    if (eqd.existeDivision(division)) {
                        response.sendRedirect("divisiones.jsp?msj=No se puede eliminar por tener equipos con esta división");
                    } else {
                        int respuesta = dd.eliminar(division);
                        if (respuesta == 1) {
                            response.sendRedirect("divisiones.jsp?msj=Division eliminada");
                        } else {
                            response.sendRedirect("divisiones.jsp?msj=Division no se pudo eliminar");
                        }
                    }
                } else {
                    response.sendRedirect("divisiones.jsp?msj=Division no existe");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("divisiones.jsp?msj=" + e.getMessage());
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
