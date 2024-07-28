package org.example;

public class Administradores {
    public String cedula;
    public String nombre;
    public String correo;
    public String contraseña;
    public Administradores() {}
    public Administradores(String cedula, String nombre, String correo, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
