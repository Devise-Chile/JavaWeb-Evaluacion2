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
import modelos.Division;

/**
 *
 * @author amaru
 */
public class DivisionDAO extends Conexion {
    public int registrar(Division division) throws SQLException{
        String sentencia = "Insert into division (nombre) values (?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, division.getNombre());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Division division) throws SQLException{
        String sentencia = "update division set nombre = ? where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, division.getNombre());
            ps.setInt(2, division.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Division division) throws SQLException{
        String sentencia = "delete from division where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, division.getCodigo());
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Division obtenerDivision(int codigo) throws SQLException{
        String sentencia = "select * from division where codigo=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Division(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public Division obtenerDivision(String nombre) throws SQLException{
        String sentencia = "select * from division where nombre=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return (new Division(rs.getInt("codigo"),rs.getString("nombre")));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    public ArrayList<Division> obtenerDivisiones() throws SQLException{
        String sentencia = "select * from division";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Division> divisiones = new ArrayList();
            while(rs.next()){
                divisiones.add(new Division(rs.getInt("codigo"),rs.getString("nombre")));
            }
            return divisiones;
        }catch(Exception e ){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
}
