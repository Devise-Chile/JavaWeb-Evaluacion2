/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ciudad;
import modelos.Posicion;

/**
 *
 * @author rodrigo
 */
public class EquipoDAO extends Conexion {
    
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
}
