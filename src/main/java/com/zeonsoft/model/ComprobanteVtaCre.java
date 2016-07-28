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
            item.getArticulo().getStock().updateStock(item.getCantidad(), item.getPrecio());
        }
    }
}
