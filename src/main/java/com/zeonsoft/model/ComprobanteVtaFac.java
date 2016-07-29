package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteVtaFac extends Comprobante {
    public ComprobanteVtaFac() {
    }

    public ComprobanteVtaFac(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            item.getArticulo().getStock().addItem(this.getFecha(), item.getCantidad() * -1, item.getPrecio());

            int cantidadArt = item.getArticulo().getStock().getCantidad();

            int newCantidad = cantidadArt + item.getCantidad() * -1;

            item.getArticulo().getStock().setCantidad(newCantidad);
        }
    }
}
