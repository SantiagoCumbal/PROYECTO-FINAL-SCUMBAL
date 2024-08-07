package org.example;

public class Canchas {
    private String codigo;
    private String nombreCancha;
    private byte[] imagen;
    private String ubicacion;
    private String estado;
    private String capacidad;
    private String horario8;
    private String cedulaReserva1;
    private String horario10;
    private String cedulaReserva2;
    private String horario12;
    private String cedulaReserva3;
    private String horario14;
    private String cedulaReserva4;
    private String horario16;
    private String cedulaReserva5;
    private String horario18;
    private String cedulaReserva6;
    public Canchas() {}

    public Canchas(String codigo, String nombreCancha, byte[] imagen, String ubicacion, String estado, String capacidad,
                   String horario8, String cedulaReserva1, String horario10, String cedulaReserva2,
                   String horario12, String cedulaReserva3, String horario14, String cedulaReserva4,
                   String horario16, String cedulaReserva5, String horario18, String cedulaReserva6) {
        this.codigo = codigo;
        this.nombreCancha = nombreCancha;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.capacidad = capacidad;
        this.horario8 = horario8;
        this.cedulaReserva1 = cedulaReserva1;
        this.horario10 = horario10;
        this.cedulaReserva2 = cedulaReserva2;
        this.horario12 = horario12;
        this.cedulaReserva3 = cedulaReserva3;
        this.horario14 = horario14;
        this.cedulaReserva4 = cedulaReserva4;
        this.horario16 = horario16;
        this.cedulaReserva5 = cedulaReserva5;
        this.horario18 = horario18;
        this.cedulaReserva6 = cedulaReserva6;
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

    public String getHorario8() {
        return horario8;
    }

    public void setHorario8(String horario8) {
        this.horario8 = horario8;
    }

    public String getCedulaReserva1() {
        return cedulaReserva1;
    }

    public void setCedulaReserva1(String cedulaReserva1) {
        this.cedulaReserva1 = cedulaReserva1;
    }

    public String getHorario10() {
        return horario10;
    }

    public void setHorario10(String horario10) {
        this.horario10 = horario10;
    }

    public String getCedulaReserva2() {
        return cedulaReserva2;
    }

    public void setCedulaReserva2(String cedulaReserva2) {
        this.cedulaReserva2 = cedulaReserva2;
    }

    public String getHorario12() {
        return horario12;
    }

    public void setHorario12(String horario12) {
        this.horario12 = horario12;
    }

    public String getCedulaReserva3() {
        return cedulaReserva3;
    }

    public void setCedulaReserva3(String cedulaReserva3) {
        this.cedulaReserva3 = cedulaReserva3;
    }

    public String getHorario14() {
        return horario14;
    }

    public void setHorario14(String horario14) {
        this.horario14 = horario14;
    }

    public String getCedulaReserva4() {
        return cedulaReserva4;
    }

    public void setCedulaReserva4(String cedulaReserva4) {
        this.cedulaReserva4 = cedulaReserva4;
    }

    public String getHorario16() {
        return horario16;
    }

    public void setHorario16(String horario16) {
        this.horario16 = horario16;
    }

    public String getCedulaReserva5() {
        return cedulaReserva5;
    }

    public void setCedulaReserva5(String cedulaReserva5) {
        this.cedulaReserva5 = cedulaReserva5;
    }

    public String getHorario18() {
        return horario18;
    }

    public void setHorario18(String horario18) {
        this.horario18 = horario18;
    }

    public String getCedulaReserva6() {
        return cedulaReserva6;
    }

    public void setCedulaReserva6(String cedulaReserva6) {
        this.cedulaReserva6 = cedulaReserva6;
    }
}
