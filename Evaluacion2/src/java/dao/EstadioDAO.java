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
import modelos.Estadio;

/**
 *
 * @author amaru
 */
public class EstadioDAO extends Conexion {
    public int registrar(Estadio estadio) throws SQLException{
        String sentencia = "Insert into estadio (nombre, cod_ciudad, capacidad) values (?,?,?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, estadio.getNombre());
            ps.setInt(2, estadio.getCiudad().getCodigo());
            ps.setInt(3, estadio.getCapacidad());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Estadio estadio) throws SQLException{
        String sentencia = "update estadio set nombre = ?, cod_ciudad = ?, capacidad = ? where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, estadio.getNombre());
            ps.setInt(2, estadio.getCiudad().getCodigo());
            ps.setInt(3, estadio.getCapacidad());
            ps.setInt(4, estadio.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Estadio estadio) throws SQLException{
        String sentencia = "delete from estadio where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, estadio.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Estadio obtenerEstadio(int codigo) throws SQLException{
        String sentencia = "select * from v_estadio where cod_estadio = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Ciudad ciudad = new Ciudad(rs.getInt("cod_ciudad"), rs.getString("nombre_ciudad"));
                return new Estadio(rs.getInt("cod_estadio"),rs.getString("nombre_estadio"), ciudad, rs.getInt("capacidad"));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public Estadio obtenerEstadio(String nombre) throws SQLException{
        String sentencia = "select * from v_estadio where nombre_estadio = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Ciudad ciudad = new Ciudad(rs.getInt("cod_ciudad"), rs.getString("nombre_ciudad"));
                return new Estadio(rs.getInt("cod_estadio"),rs.getString("nombre_estadio"), ciudad, rs.getInt("capacidad"));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public ArrayList<Estadio> obtenerEstadios() throws SQLException{
        String sentencia = "select * from v_estadio";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Estadio> estadios = new ArrayList();
            while(rs.next()){
                Ciudad ciudad = new Ciudad(rs.getInt("cod_ciudad"), rs.getString("nombre_ciudad"));
                estadios.add(new Estadio(rs.getInt("cod_estadio"),rs.getString("nombre_estadio"), ciudad, rs.getInt("capacidad")));
            }
            return estadios;
        }catch(Exception e ){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public boolean existeCiudad(Ciudad ciudad) throws SQLException{
        try{
            String sentencia = "select * from estadio where cod_ciudad= ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, ciudad.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
}
