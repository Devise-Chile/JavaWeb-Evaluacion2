/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import modelos.Usuario;

/**
 *
 * @author amaru
 */
public class UsuarioUtil {
    
    public static ArrayList<Usuario> usuarios = new ArrayList();
    
    public static String createPassword(){
        return "XXXXXXXX";
    }
    
    public static Usuario obtenerUsuario(String run){
        Usuario temporal = null;
        for(Usuario u:usuarios){
            if(u.getRun().equals(run)){
                temporal = u;
            }
        }
        return temporal;
    }
}
