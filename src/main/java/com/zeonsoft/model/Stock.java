package com.zeonsoft.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<ItemStock> items;

    public Stock() {
        this.items = new ArrayList<ItemStock>();
    }

    public List<ItemStock> getItems() {
        return items;
    }

    public void setItems(List<ItemStock> items) {
        this.items = items;
    }

    public void increaseStock(Date fecha, int cantidad, float precio) {
        ItemStock item = new ItemStock(fecha, cantidad, precio);

        items.add(item);
    }

    public void decreaseStock(int cantidad) {
        for (ItemStock item: items) {
            int cantidadDisponible = item.getCantidadDisponible();

            if (cantidadDisponible >= cantidad) {
                item.setCantidadDisponible(cantidadDisponible - cantidad);
                break;
            } else {
                item.setCantidadDisponible(0);
                cantidad = cantidad - cantidadDisponible;
            }
        }
    }

    public void updateStock(int cantidad, float precio) {
        for (ItemStock item: items) {
            int cantidadDisponible = item.getCantidadDisponible();

            if (cantidad > 0) {
                if (cantidadDisponible > 0) {
                    float valor = (cantidadDisponible * item.getPrecio()) + (cantidad * precio);

                    int newCantidad = cantidadDisponible + cantidad;
                    float newPrecio = valor / newCantidad;

                    item.setCantidadDisponible(newCantidad);
                    item.setPrecio(newPrecio);
                    break;
                }
            } else {
                if (cantidadDisponible > 0 && cantidadDisponible >= cantidad * -1) {
                    float valor = (cantidadDisponible * item.getPrecio()) + (cantidad * precio);

                    int newCantidad = cantidadDisponible + cantidad;
                    float newPrecio = valor / newCantidad;

                    item.setCantidadDisponible(newCantidad);
                    item.setPrecio(newPrecio);
                    break;
                } else if (cantidadDisponible > 0 && cantidadDisponible < cantidad * -1){
                    item.setCantidadDisponible(0);
                    cantidad = cantidad - cantidadDisponible;
                }
            }
        }
    }
}
