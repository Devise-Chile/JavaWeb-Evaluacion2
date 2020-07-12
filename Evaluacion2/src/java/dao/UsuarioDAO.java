/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Usuario;

/**
 *
 * @author amaru
 */
public class UsuarioDAO extends Conexion {
    
    /*public int registrarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into usuario values ('"+u.getId()+"','"+u.getNombre()+"',"
                + "'"+u.getApellido()+"','"+u.getPassword()+"')";
        conectar();
        int r = obtenerStatement().executeUpdate(sentencia);
        desconectar();
        return r;
    }*/
    public int registrarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "insert into usuario values (?,?,?,?)";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, u.getRun());
        ps.setString(2, u.getNombre());
        ps.setString(3, u.getApellido());
        ps.setString(4, u.getPassword());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "update usuario set nombre=?,apellido=?,password=? where run=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getApellido());
        ps.setString(3, u.getPassword());
        ps.setString(4, u.getRun());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public int eliminarUsuario(Usuario u) throws ClassNotFoundException, SQLException{
        String sentencia = "delete from usuario where run=?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, u.getRun());
        int r = ps.executeUpdate();
        return r;
        }catch(Exception e){
            return -1;
        }finally{
        desconectar();
        }
    }
    public ArrayList<Usuario> obtenerUsuarios() throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ResultSet rs = ps.executeQuery();
        ArrayList<Usuario> lista = new ArrayList();
        while(rs.next()){
            lista.add(new Usuario(rs.getString("run"),rs.getString("nombre"),
                    rs.getString("apellido"),rs.getString("password")));
        }
        return lista;
        }catch(Exception e){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    public Usuario obtenerUsuario(String run) throws ClassNotFoundException, SQLException{
        String sentencia = "select * from usuario where run = ?";
        try{
        conectar();
        PreparedStatement ps= obtenerPS(sentencia);
        ps.setString(1, run);
        ResultSet rs = ps.executeQuery();
        Usuario u = null;
        if(rs.next()){
           u = new Usuario(rs.getString("run"),rs.getString("nombre"), rs.getString("apellido"),rs.getString("password"));
        }
        return u;
        }catch(Exception e){
            return null;
        }finally{
            desconectar();
        }
    }
    
}
