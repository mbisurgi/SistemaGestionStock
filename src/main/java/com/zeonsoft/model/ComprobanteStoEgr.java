package com.zeonsoft.model;

import com.zeonsoft.controller.SistemaGestionStock;

import java.sql.Date;

public class ComprobanteStoEgr extends Comprobante {
    public ComprobanteStoEgr() {
    }

    public ComprobanteStoEgr(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    @Override
    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            Articulo art = SistemaGestionStock.getInstancia().buscarArticulo(item.getArticulo().getNroArticulo());

            art.getStock().addItem(this.getFecha(), item.getCantidad(), item.getPrecio());

            int cantidadArt = art.getStock().getCantidad();

            int newCantidad = 0;

            newCantidad = cantidadArt + item.getCantidad() * -1;

            art.getStock().setCantidad(newCantidad);
        }
    }
}
