package com.zeonsoft.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Comprobante {
    private Date fecha;
    private String entidad;
    private String nroComprobante;
    private List<ItemComprobante> items;

    public Comprobante() {
    }

    public Comprobante(Date fecha, String entidad, String nroComprobante) {
        this.fecha = fecha;
        this.entidad = entidad;
        this.nroComprobante = nroComprobante;
        this.items = new ArrayList<ItemComprobante>();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public List<ItemComprobante> getItems() {
        return items;
    }

    public void setItems(List<ItemComprobante> items) {
        this.items = items;
    }

    public abstract void updateStock();

    @Override
    public String toString() {
        return "Comprobante{" +
                "fecha=" + fecha +
                ", entidad='" + entidad + '\'' +
                ", nroComprobante='" + nroComprobante + '\'' +
                '}';
    }
}
