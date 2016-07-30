package com.zeonsoft.model;

import java.sql.Date;

public class ItemMargen {
    private int idItem;
    private Date fecha;
    private int cantidad;
    private float precioCpa;
    private float precioVta;

    public ItemMargen() {
    }

    public ItemMargen(Date fecha, int cantidad, float precioCpa, float precioVta) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precioCpa = precioCpa;
        this.precioVta = precioVta;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioCpa() {
        return precioCpa;
    }

    public void setPrecioCpa(float precioCpa) {
        this.precioCpa = precioCpa;
    }

    public float getPrecioVta() {
        return precioVta;
    }

    public void setPrecioVta(float precioVta) {
        this.precioVta = precioVta;
    }

    @Override
    public String toString() {
        return "ItemMargen{" +
                "idItem=" + idItem +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", precioCpa=" + precioCpa +
                ", precioVta=" + precioVta +
                '}';
    }
}
