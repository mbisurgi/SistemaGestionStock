package com.zeonsoft.model;

import com.zeonsoft.controller.SistemaGestionStock;

import java.sql.Date;

public class ComprobanteVtaFac extends Comprobante {
    public ComprobanteVtaFac() {
    }

    public ComprobanteVtaFac(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            Articulo art = SistemaGestionStock.getInstancia().buscarArticulo(item.getArticulo().getNroArticulo());

            float pcioCpa = art.getStock().getCosto();

            art.getStock().addItem(this.getFecha(), item.getCantidad() * -1, pcioCpa);

            art.getMargen().addItem(this.getFecha(), item.getCantidad(), pcioCpa, item.getPrecio());

            int cantidadArt = art.getStock().getCantidad();

            int newCantidad = cantidadArt + item.getCantidad() * -1;

            art.getStock().setCantidad(newCantidad);
        }
    }
}
