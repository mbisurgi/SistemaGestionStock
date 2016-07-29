package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteVtaCre extends Comprobante {
    public ComprobanteVtaCre() {
    }

    public ComprobanteVtaCre(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            item.getArticulo().getStock().addItem(this.getFecha(), item.getCantidad(), item.getPrecio());

            int cantidadArt = item.getArticulo().getStock().getCantidad();

            int newCantidad = cantidadArt + item.getCantidad();

            item.getArticulo().getStock().setCantidad(newCantidad);
        }
    }
}
