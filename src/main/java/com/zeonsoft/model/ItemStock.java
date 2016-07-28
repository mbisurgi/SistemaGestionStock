package com.zeonsoft.model;

import java.sql.Date;

public class ItemStock {
    private int idItem;
    private Date fecha;
    private int cantidad;
    private float precio;
    private int cantidadDisponible;

    public ItemStock() {
    }

    public ItemStock(Date fecha, int cantidad, float precio) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.precio = precio;
        this.cantidadDisponible = cantidad;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "ItemStock{" +
                "idItem=" + idItem +
                ", fecha=" + fecha +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
