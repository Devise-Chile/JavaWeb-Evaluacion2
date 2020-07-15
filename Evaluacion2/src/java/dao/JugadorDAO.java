package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Equipo;
import modelos.Jugador;
import modelos.Posicion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodrigo
 */
public class JugadorDAO extends Conexion {
    public int registrar(Jugador jugador) throws SQLException{
        String sentencia = "Insert into jugador (nombre, apellido, f_nacimiento, cod_posicion, sueldo, cod_equipo) values (?,?,?,?,?,?)";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getApellido());
            ps.setString(3, jugador.getFechaNacimiento());
            ps.setInt(4, jugador.getPosicion().getCodigo());
            ps.setInt(5, jugador.getSueldo());
            ps.setInt(6, jugador.getEquipo().getCodigo());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int modificar(Jugador jugador) throws SQLException{
        String sentencia = "update jugador set nombre=?, apellido=?, f_nacimiento=?, cod_posicion=?, sueldo=?, cod_equipo=? where id=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getApellido());
            ps.setString(3, jugador.getFechaNacimiento());
            ps.setInt(4, jugador.getPosicion().getCodigo());
            ps.setInt(5, jugador.getSueldo());
            ps.setInt(6, jugador.getEquipo().getCodigo());
            ps.setInt(7, jugador.getId());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public int eliminar(Jugador jugador) throws SQLException{
        String sentencia = "delete from jugador where id = ?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, jugador.getId());
            
            return ps.executeUpdate();
        }catch(Exception e){
            return -1;
        }finally{
            desconectar();
        }
    }
    public Jugador obtenerJugador(int id) throws SQLException{
        String sentencia = "select * from v_jugador where id=?";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Posicion pos = new Posicion(rs.getInt("cod_posicion"), rs.getString("nombre_posicion"));
                Equipo equipo = new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre_equipo"), null, null, null);
                
                return (new Jugador(rs.getInt("id"),rs.getString("nombre_jugador"),rs.getString("apellido_jugador"), rs.getString("f_nacimiento"),
                    pos, rs.getInt("sueldo"), equipo));
            }else{
                return null;
            }
        }catch(Exception e ){
            return null;
        }finally{
            desconectar();
        }
    }
    
    public ArrayList<Jugador> obtenerJugadores() throws SQLException{
        String sentencia = "select * from v_jugador";
        try{
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ResultSet rs = ps.executeQuery();
            ArrayList<Jugador> jugadores = new ArrayList();
            while(rs.next()){
                Posicion pos = new Posicion(rs.getInt("cod_posicion"), rs.getString("nombre_posicion"));
                Equipo equipo = new Equipo(rs.getInt("cod_equipo"), rs.getString("nombre_equipo"), null, null, null);
                
                jugadores.add(new Jugador(rs.getInt("id"),rs.getString("nombre_jugador"),rs.getString("apellido_jugador"), rs.getString("f_nacimiento"),
                    pos, rs.getInt("sueldo"), equipo));
            }
            return jugadores;
        }catch(Exception e ){
            return new ArrayList();
        }finally{
            desconectar();
        }
    }
    
    public boolean existePosicion(Posicion posicion) throws SQLException{
        try{
            String sentencia = "select * from jugador where cod_posicion = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, posicion.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
    
    public boolean existeEquipo(Equipo equipo) throws SQLException{
        try{
            String sentencia = "select * from jugador where cod_equipo = ?";
            conectar();
            PreparedStatement ps = obtenerPS(sentencia);
            ps.setInt(1, equipo.getCodigo());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }catch(Exception e){
            return false;
        }finally{
            desconectar();
        }
    }
}
