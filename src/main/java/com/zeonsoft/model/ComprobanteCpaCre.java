package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteCpaCre extends Comprobante {
    public ComprobanteCpaCre() {
    }

    public ComprobanteCpaCre(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            item.getArticulo().getStock().updateStock(item.getCantidad() * -1, item.getPrecio());
        }
    }
}
