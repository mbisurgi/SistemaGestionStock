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
            item.getArticulo().getStock().increaseStock(this.getFecha(), item.getCantidad(), item.getPrecio());
        }
    }
}
