package com.zeonsoft.model;

import com.zeonsoft.controller.SistemaGestionStock;

import java.sql.Date;

public class ComprobanteCpaCre extends Comprobante {
    public ComprobanteCpaCre() {
    }

    public ComprobanteCpaCre(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    public void updateStock() {
        for (ItemComprobante item: this.getItems()) {
            Articulo art = SistemaGestionStock.getInstancia().buscarArticulo(item.getArticulo().getNroArticulo());

            art.getStock().addItem(this.getFecha(), item.getCantidad() * -1, item.getPrecio());

            int cantidadArt = art.getStock().getCantidad();
            float costoArt = art.getStock().getCosto();

            float costoTotal = 0;
            int newCantidad = 0;

            if (costoArt < item.getPrecio()) {
                costoTotal = (cantidadArt * costoArt) + (item.getCantidad() * item.getPrecio());
                newCantidad = cantidadArt + item.getCantidad();
            } else {
                costoTotal = (cantidadArt * costoArt) + (item.getCantidad() * -1 * item.getPrecio());
                newCantidad = cantidadArt + item.getCantidad() * -1;
            }

            float newCosto = 0;

            if (newCantidad == 0) {
                newCosto = 0;
            } else {
                newCosto = costoTotal / newCantidad;
            }

            newCantidad = cantidadArt + item.getCantidad() * -1;

            art.getStock().setCantidad(newCantidad);
            art.getStock().setCosto(newCosto);
        }
    }
}
