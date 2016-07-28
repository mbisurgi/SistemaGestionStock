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
            item.getArticulo().getStock().decreaseStock(item.getCantidad());
        }
    }
}
