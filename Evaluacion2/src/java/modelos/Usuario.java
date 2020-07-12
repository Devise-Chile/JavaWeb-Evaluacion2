/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author amaru
 */
public class Usuario {
    private String run;
    private String nombre;
    private String apellido;
    private String password;
    
    
    
    public Usuario(String run, String nombre, String apellido, String password) {
        this.run = run;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public Usuario(String run, String password) {
        this.run = run;
        this.password = password;
    }

    public Usuario() {
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "run=" + run + ", nombre=" + nombre + ", apellido=" + apellido;
    }
       
}
