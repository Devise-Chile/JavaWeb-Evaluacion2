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
import modelos.Ciudad;

/**
 *
 * @author amaru
 */
public class CiudadDAO extends Conexion {
    public int registrar(Ciudad ciudad) throws SQLException{
        String sentencia = "Insert into ciudad (nombre) values (?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, ciudad.getNombre());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Ciudad ciudad) throws SQLException{
        String sentencia = "update ciudad set nombre = ? where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, ciudad.getNombre());
            ps.setInt(2, ciudad.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Ciudad ciudad) throws SQLException{
        String sentencia = "delete from ciudad where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, ciudad.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Ciudad obtenerCiudad(int codigo) throws SQLException{
        String sentencia = "select * from ciudad where codigo=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Ciudad(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public Ciudad obtenerCiudad(String nombre) throws SQLException{
        String sentencia = "select * from ciudad where nombre=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Ciudad(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public ArrayList<Ciudad> obtenerCiudades() throws SQLException{
        String sentencia = "select * from ciudad";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ciudad> ciudades = new ArrayList();
            while(rs.next()){
                ciudades.add(new Ciudad(rs.getInt("codigo"),rs.getString("nombre")));
            }
            return ciudades;
        }catch(Exception e ){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }


}
