package com.zeonsoft.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private int cantidad;
    private float costo;
    private List<ItemStock> items;

    public Stock() {
        this.cantidad = 0;
        this.costo = 0;
        this.items = new ArrayList<ItemStock>();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public List<ItemStock> getItems() {
        return items;
    }

    public void setItems(List<ItemStock> items) {
        this.items = items;
    }

    public void addItem(Date fecha, int cantidad, float precio) {
        ItemStock item = new ItemStock(fecha, cantidad, precio);

        items.add(item);
    }
}
