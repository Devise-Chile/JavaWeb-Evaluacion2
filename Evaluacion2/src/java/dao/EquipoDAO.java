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
import modelos.Division;
import modelos.Equipo;
import modelos.Estadio;

/**
 *
 * @author rodrigo
 */
public class EquipoDAO extends Conexion {
    
    public int registrar(Equipo equipo) throws SQLException{
        String sentencia = "Insert into equipo(nombre, cod_ciudad, cod_estadio, cod_division) values (?,?,?,?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, equipo.getNombre());
            ps.setInt(2, equipo.getCiudad().getCodigo());
            ps.setInt(3, equipo.getEstadio().getCodigo());
            ps.setInt(4, equipo.getDivision().getCodigo());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Equipo equipo) throws SQLException{
        String sentencia = "update equipo set nombre=?, cod_ciudad=?, cod_estadio=?, cod_division=? where codigo=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, equipo.getNombre());
            ps.setInt(2, equipo.getCiudad().getCodigo());
            ps.setInt(3, equipo.getEstadio().getCodigo());
            ps.setInt(4, equipo.getDivision().getCodigo());
            ps.setInt(7, equipo.getCodigo());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Equipo equipo) throws SQLException{
        String sentencia = "delete from equipo where codigo = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, equipo.getCodigo());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Equipo obtenerEquipo(int codigo) throws SQLException{
        String sentencia = "select * from v_equipo where codigo=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Ciudad ciudad = new Ciudad(rs.getInt("cod_ciudad"), rs.getString("nombre_ciudad"));
                Estadio estadio = new Estadio(rs.getInt("cod_estadio"), rs.getString("nombre_estadio"), null, rs.getInt("capacidad"));
                Division division = new Division(rs.getInt("cod_division"), rs.getString("nombre_division"));
                
                return (new Equipo(rs.getInt("codigo"), rs.getString("nombre_equipo"), ciudad, estadio, division));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public ArrayList<Equipo> obtenerEquipos() throws SQLException{
        String sentencia = "select * from v_equipo";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Equipo> equipos = new ArrayList();
            while(rs.next()){
                Ciudad ciudad = new Ciudad(rs.getInt("cod_ciudad"), rs.getString("nombre_ciudad"));
                Estadio estadio = new Estadio(rs.getInt("cod_estadio"), rs.getString("nombre_estadio"), null, rs.getInt("capacidad"));
                Division division = new Division(rs.getInt("cod_division"), rs.getString("nombre_division"));
                
                equipos.add(new Equipo(rs.getInt("codigo"), rs.getString("nombre_equipo"), ciudad, estadio, division));
            }
            return equipos;
        }catch(Exception e ){
            return new ArrayList<Equipo>();
        }finally{
            desconectar();
        }
    }
    
    public boolean existeCiudad(Ciudad ciudad) throws SQLException{
        try{
            String sentencia = "select * from equipo where cod_ciudad= ?";
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
    
    public boolean existeDivision(Division division) throws SQLException{
        try{
            String sentencia = "select * from equipo where cod_division= ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, division.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
}
