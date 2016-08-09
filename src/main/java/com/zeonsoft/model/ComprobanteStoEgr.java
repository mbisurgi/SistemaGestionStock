package com.zeonsoft.model;

import java.sql.Date;

public class ComprobanteStoEgr extends Comprobante {
    public ComprobanteStoEgr() {
    }

    public ComprobanteStoEgr(Date fecha, String entidad, String nroComprobante) {
        super(fecha, entidad, nroComprobante);
    }

    @Override
    public void updateStock() {

    }
}
