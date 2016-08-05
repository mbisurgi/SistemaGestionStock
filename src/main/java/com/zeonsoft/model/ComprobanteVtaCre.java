package com.zeonsoft.model;

import com.zeonsoft.controller.SistemaGestionStock;

import java.sql.Date;

public class ComprobanteVtaCre extends Comprobante {
    public ComprobanteVtaCre() {
    }

    public ComprobanteVtaCre(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            Articulo art = SistemaGestionStock.getInstancia().buscarArticulo(item.getArticulo().getNroArticulo());

            float pcioCpa = art.getStock().getCosto();

            art.getStock().addItem(this.getFecha(), item.getCantidad(), pcioCpa);

            art.getMargen().addItem(this.getFecha(), item.getCantidad() * -1, pcioCpa, item.getPrecio());

            int cantidadArt = art.getStock().getCantidad();

            int newCantidad = cantidadArt + item.getCantidad();

            art.getStock().setCantidad(newCantidad);
        }
    }
}
