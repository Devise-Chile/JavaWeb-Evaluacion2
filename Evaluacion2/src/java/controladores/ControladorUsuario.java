/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author amaru
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

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
        if(request.getParameter("accion")!=null){
        String accion = request.getParameter("accion");
        switch(accion){
            case "1": iniciarSesion(request,response);
                break;
            case "2": registrar(request,response);
                break;
            case "3": modificar(request,response);
                break;
            case "4": eliminar(request,response);
                break;
            default: response.sendRedirect("index.jsp?msj=Acción no permitida");
        }
        }else{
            response.sendRedirect("index.jsp?msj=Acción no permitida");
        }
    }
  
            
    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        String run = request.getParameter("run").trim();
        if(run.equals("")){
            response.sendRedirect("intranet.jsp?msj=Campos incompletos");
        }else{
            UsuarioDAO ud = new UsuarioDAO();
            Usuario u = new Usuario();
            u.setRun(run);
            int respuesta = ud.eliminarUsuario(u);
            if(respuesta==1){
            HttpSession sesion = request.getSession();
            response.sendRedirect(((Usuario)sesion.getAttribute("usuario")).getRun().equals(run)?
                   "Salir":"intranet.jsp?msj=Usuario eliminado");
            }else{
                response.sendRedirect("intranet.jsp?msj=No se pudo eliminar el usuario");
            }
        }
        }catch(Exception e){
             response.sendRedirect("intranet.jsp?msj="+e.getMessage());
        }
    }
    private void modificar(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        String run = request.getParameter("run").trim();
        String nombre = request.getParameter("nombre").trim();
        String apellido = request.getParameter("apellido").trim();
        String password = request.getParameter("password").trim();
        if(run.equals("")||nombre.isEmpty()||apellido.length()==0){
            response.sendRedirect("intranet.jsp?msj=Campos incompletos");
        }else{
            UsuarioDAO ud = new UsuarioDAO();
            if(password.equals("")){
                password = ud.obtenerUsuario(run).getPassword();
            }
            Usuario u = new Usuario(run,nombre,apellido,password);
            int respuesta = ud.modificarUsuario(u);
            response.sendRedirect(respuesta==1?"intranet.jsp?msj=Usuario modificado":
                    "intranet.jsp?msj=Usuario no se pudo modificar");
        }
        }catch(Exception e){
            response.sendRedirect("intranet.jsp?msj="+e.getMessage());
        }
    }
    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try{
        String run = request.getParameter("run");
        String password = request.getParameter("password");
        UsuarioDAO ud = new UsuarioDAO();
        Usuario temporal= ud.obtenerUsuario(run);
        if(temporal!=null){
            if(temporal.getPassword().equals(password)){
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", temporal);
            
            response.sendRedirect("intranet.jsp");
            }else{
                response.sendRedirect("index.jsp?msj=Password incorrecto");
            }
        }else{
            response.sendRedirect("index.jsp?msj=Run inexistente");
        }
        }catch(Exception e){
            response.sendRedirect("index.jsp?msj="+e.getMessage());
        }
    }
        private void registrar(HttpServletRequest request, HttpServletResponse response) throws IOException{
           try{
            String run = request.getParameter("run").trim();
            String nombre = request.getParameter("nombre").trim();
            String apellido = request.getParameter("apellido").trim();
            String password = request.getParameter("password").trim();
            if(run.equals("")||nombre.isEmpty()||apellido.length()==0||password.isEmpty()){
                response.sendRedirect("registro.jsp?msj=Campos incompletos");
            }else{
                Usuario usuarioNuevo = new Usuario(run,nombre,apellido,password);
                UsuarioDAO ud = new UsuarioDAO();
                HttpSession sesion = request.getSession();
                if(ud.obtenerUsuario(usuarioNuevo.getRun())==null){
                    int respuesta = ud.registrarUsuario(usuarioNuevo);
                    if(respuesta==1){
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario registrado"
                            :"index.jsp?msj=Usuario registrado, inicie sesion");
                    }else{
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario no se puede registar"
                            :"index.jsp?msj=Usuario no se puede registrar");
                    }

                }else{
                    response.sendRedirect((sesion.getAttribute("usuario")!=null)?"intranet.jsp?msj=Usuario ya existe":
                            "registro.jsp?msj=Usuario ya existe");
                }
            }
           }catch(Exception e){
               response.sendRedirect("index.jsp?msj="+e.getMessage());
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
