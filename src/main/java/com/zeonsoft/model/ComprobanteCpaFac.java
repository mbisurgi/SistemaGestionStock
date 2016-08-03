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

            float costoTotal = 0;
            int newCantidad = 0;

            if (cantidadArt < 0) {
                costoTotal = (cantidadArt * -1 * costoArt) + (item.getCantidad() * item.getPrecio());
                newCantidad = cantidadArt * -1 + item.getCantidad();
            } else {
                costoTotal = (cantidadArt * costoArt) + (item.getCantidad() * item.getPrecio());
                newCantidad = cantidadArt + item.getCantidad();
            }

            float newCosto = costoTotal / newCantidad;

            newCantidad = cantidadArt + item.getCantidad();

            art.getStock().setCantidad(newCantidad);
            art.getStock().setCosto(newCosto);
        }
    }
}
