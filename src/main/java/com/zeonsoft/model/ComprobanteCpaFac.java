package com.zeonsoft.model;

import com.zeonsoft.controller.SistemaGestionStock;

import java.sql.Date;

public class ComprobanteCpaFac extends Comprobante {
    public ComprobanteCpaFac() {
    }

    public ComprobanteCpaFac(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            Articulo art = SistemaGestionStock.getInstancia().buscarArticulo(item.getArticulo().getNroArticulo());

            art.getStock().addItem(this.getFecha(), item.getCantidad(), item.getPrecio());

            int cantidadArt = art.getStock().getCantidad();
            float costoArt = art.getStock().getCosto();

            float costoTotal = (cantidadArt * costoArt) + (item.getCantidad() * item.getPrecio());
            int newCantidad = cantidadArt + item.getCantidad();
            float newCosto = costoTotal / newCantidad;

            art.getStock().setCantidad(newCantidad);
            art.getStock().setCosto(newCosto);
        }
    }
}
