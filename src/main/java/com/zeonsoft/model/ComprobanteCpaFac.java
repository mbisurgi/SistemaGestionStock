package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteCpaFac extends Comprobante {
    public ComprobanteCpaFac() {
    }

    public ComprobanteCpaFac(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            item.getArticulo().getStock().addItem(this.getFecha(), item.getCantidad(), item.getPrecio());

            int cantidadArt = item.getArticulo().getStock().getCantidad();
            float costoArt = item.getArticulo().getStock().getCosto();

            float costoTotal = (cantidadArt * costoArt) + (item.getCantidad() * item.getPrecio());
            int newCantidad = cantidadArt + item.getCantidad();
            float newCosto = costoTotal / newCantidad;

            item.getArticulo().getStock().setCantidad(newCantidad);
            item.getArticulo().getStock().setCosto(newCosto);
        }
    }
}
