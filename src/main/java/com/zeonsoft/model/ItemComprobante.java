package com.zeonsoft.model;

public class ItemComprobante {
    private int idItem;
    private Articulo articulo;
    private int cantidad;
    private float precio;

    public ItemComprobante() {
    }

    public ItemComprobante(Articulo articulo, int cantidad, float precio) {
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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

    @Override
    public String toString() {
        return "ItemComprobante{" +
                "idItem=" + idItem +
                ", articulo=" + articulo.getNombreArticulo() +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
