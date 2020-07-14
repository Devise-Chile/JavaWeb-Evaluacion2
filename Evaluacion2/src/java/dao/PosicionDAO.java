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
import modelos.Posicion;

/**
 *
 * @author amaru
 */
public class PosicionDAO extends Conexion {
    public int registrar(Posicion posicion) throws SQLException{
        String sentencia = "Insert into posicion (nombre) values (?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, posicion.getNombre());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Posicion posicion) throws SQLException{
        String sentencia = "update posicion set nombre = ? where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, posicion.getNombre());
            ps.setInt(2, posicion.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Posicion posicion) throws SQLException{
        String sentencia = "delete from posicion where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, posicion.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Posicion obtenerPosicion(int codigo) throws SQLException{
        String sentencia = "select * from posicion where codigo=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Posicion(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public Posicion obtenerPosicion(String nombre) throws SQLException{
        String sentencia = "select * from posicion where nombre=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Posicion(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public ArrayList<Posicion> obtenerPosiciones() throws SQLException{
        String sentencia = "select * from posicion";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Posicion> posiciones = new ArrayList();
            while(rs.next()){
                posiciones.add(new Posicion(rs.getInt("codigo"),rs.getString("nombre")));
            }
            return posiciones;
        }catch(Exception e ){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
}
