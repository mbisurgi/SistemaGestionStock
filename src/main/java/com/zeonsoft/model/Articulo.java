package com.zeonsoft.model;

public class Articulo {
    private String nroArticulo;
    private String nombreArticulo;
    private Stock stock;
    private Margen margen;

    public Articulo() {
    }

    public Articulo(String nroArticulo, String nombreArticulo) {
        this.nroArticulo = nroArticulo;
        this.nombreArticulo = nombreArticulo;
        this.stock = new Stock();
        this.margen = new Margen();
    }

    public String getNroArticulo() {
        return nroArticulo;
    }

    public void setNroArticulo(String nroArticulo) {
        this.nroArticulo = nroArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Margen getMargen() {
        return margen;
    }

    public void setMargen(Margen margen) {
        this.margen = margen;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "nroArticulo='" + nroArticulo + '\'' +
                ", nombreArticulo='" + nombreArticulo + '\'' +
                ", unidades='" + this.stock.getCantidad() + '\'' +
                ", costo='" + this.stock.getCosto() + '\'' +
                ", margen='" + this.margen.getMargen() + '\'' +
                '}';
    }
}
