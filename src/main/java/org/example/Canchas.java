package org.example;

public class Canchas {
    public String codigo;
    public String nombreCancha;
    public byte[] imagen;
    public String ubicacion;
    public String estado;
    public String capacidad;
    public Canchas() {}

    public Canchas(String codigo, String nombreCancha, byte[] imagen, String ubicacion, String estado, String capacidad) {
        this.codigo = codigo;
        this.nombreCancha = nombreCancha;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public void setNombreCancha(String nombreCancha) {
        this.nombreCancha = nombreCancha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }
}
