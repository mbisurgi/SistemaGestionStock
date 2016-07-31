package com.zeonsoft.model;

import java.text.DecimalFormat;

public class ArticuloView {
    private String nro;
    private String articulo;
    private int stockUni;
    private String stock$;
    private int ventaUni;
    private String venta$;
    private String cmv$;
    private String margen$;
    private String margenPor;

    public ArticuloView(String nro, String articulo, int stockUni, float stock$, int ventaUni, float venta$, float cmv$, float margen$) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.nro = nro;
        this.articulo = articulo;
        this.stockUni = stockUni;
        this.stock$ = df.format(stock$);
        this.ventaUni = ventaUni;
        this.venta$ = df.format(venta$);
        this.cmv$ = df.format(cmv$);
        this.margen$ = df.format(margen$);
        this.margenPor = df.format((margen$ / venta$) * 100);
    }

    public String getNro() {
        return nro;
    }

    public String getArticulo() {
        return articulo;
    }

    public int getStockUni() {
        return stockUni;
    }

    public String getStock$() {
        return stock$;
    }

    public int getVentaUni() {
        return ventaUni;
    }

    public String getVenta$() {
        return venta$;
    }

    public String getCmv$() {
        return cmv$;
    }

    public String getMargen$() {
        return margen$;
    }

    public String getMargenPor() {
        return margenPor;
    }
}
