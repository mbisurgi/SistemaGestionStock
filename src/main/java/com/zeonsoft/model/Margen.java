package com.zeonsoft.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Margen {
    private List<ItemMargen> items;

    public Margen() {
        this.items = new ArrayList<ItemMargen>();
    }

    public List<ItemMargen> getItems() {
        return items;
    }

    public void setItems(List<ItemMargen> items) {
        this.items = items;
    }

    public void addItem(Date fecha, int cantidad, float precioCpa, float precioVta) {
        ItemMargen item = new ItemMargen(fecha, cantidad, precioCpa, precioVta);

        items.add(item);
    }

    public float getMargen() {
        float margen = 0;

        for (ItemMargen item: items) {
            margen = margen + (item.getPrecioVta() - item.getPrecioCpa()) * item.getCantidad();
        }

        return margen;
    }
}
