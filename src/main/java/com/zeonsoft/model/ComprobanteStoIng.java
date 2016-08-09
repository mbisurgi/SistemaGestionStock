package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteStoIng extends Comprobante {
    public ComprobanteStoIng() {
    }

    public ComprobanteStoIng(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    @Override
    public void updateStock() {

    }
}
