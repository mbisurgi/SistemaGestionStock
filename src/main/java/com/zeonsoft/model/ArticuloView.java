package com.zeonsoft.model;

import java.text.DecimalFormat;

public class ArticuloView {
    private String nro;
    private String articulo;
    private Integer stockUni;
    private Float stock$;
    private Integer ventaUni;
    private Float venta$;
    private Float cmv$;
    private Float margen$;
    private Float margenPor;

    public ArticuloView(String nro, String articulo, int stockUni, float stock$, int ventaUni, float venta$, float cmv$, float margen$) {
        this.nro = nro;
        this.articulo = articulo;
        this.stockUni = stockUni;
        this.stock$ = stock$;
        this.ventaUni = ventaUni;
        this.venta$ = venta$;
        this.cmv$ = cmv$;
        this.margen$ = margen$;
        this.margenPor = (margen$ / venta$) * 100;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public Integer getStockUni() {
        return stockUni;
    }

    public void setStockUni(Integer stockUni) {
        this.stockUni = stockUni;
    }

    public Float getStock$() {
        return stock$;
    }

    public void setStock$(Float stock$) {
        this.stock$ = stock$;
    }

    public Integer getVentaUni() {
        return ventaUni;
    }

    public void setVentaUni(Integer ventaUni) {
        this.ventaUni = ventaUni;
    }

    public Float getVenta$() {
        return venta$;
    }

    public void setVenta$(Float venta$) {
        this.venta$ = venta$;
    }

    public Float getCmv$() {
        return cmv$;
    }

    public void setCmv$(Float cmv$) {
        this.cmv$ = cmv$;
    }

    public Float getMargen$() {
        return margen$;
    }

    public void setMargen$(Float margen$) {
        this.margen$ = margen$;
    }

    public Float getMargenPor() {
        return margenPor;
    }

    public void setMargenPor(Float margenPor) {
        this.margenPor = margenPor;
    }
}
