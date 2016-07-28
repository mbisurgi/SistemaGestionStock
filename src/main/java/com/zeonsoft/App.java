package com.zeonsoft;

import com.zeonsoft.model.*;
import com.zeonsoft.model.sistema.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App
{
    private List<Comprobante> comprobantes;
    private List<Articulo> articulos;

    public static void main( String[] args )
    {
        App app = new App();

        app.init();
        //app.mostrarDatos();
        app.actualizarStock();
        app.mostrarStock();
    }

    public App() {
        comprobantes = new ArrayList<Comprobante>();
        articulos = new ArrayList<Articulo>();
    }

    private void init() {
        //CREACION ARTICULOS
        Articulo art1 = new Articulo("1001", "Levadura");
        Articulo art2 = new Articulo("1002", "Margarina");

        articulos.add(art1);
        articulos.add(art2);

        //CREACION FACTURAS DE COMPRA
        Comprobante compCpaFac1 = new ComprobanteCpaFac(Date.valueOf("2016-07-01"), "100001", "A000100004352");
        Comprobante compCpaFac2 = new ComprobanteCpaFac(Date.valueOf("2016-07-01"), "100001", "A000100004712");
        Comprobante compCpaFac3 = new ComprobanteCpaFac(Date.valueOf("2016-07-05"), "100001", "A000100004891");

            //ITEMS FACTURAS DE COMPRA
        ItemComprobante item1 = new ItemComprobante(art1, 10, 50);
        ItemComprobante item2 = new ItemComprobante(art2, 12, 55);
        ItemComprobante item3 = new ItemComprobante(art1, 5, 52);
        ItemComprobante item4 = new ItemComprobante(art1, 5, 55);

        compCpaFac1.getItems().add(item1);
        compCpaFac1.getItems().add(item2);
        compCpaFac2.getItems().add(item3);
        compCpaFac3.getItems().add(item4);

        comprobantes.add(compCpaFac1);
        comprobantes.add(compCpaFac2);
        comprobantes.add(compCpaFac3);

        //CREACION FACTURAS DE VENTA
        Comprobante compVtaFac1 = new ComprobanteVtaFac(Date.valueOf("2016-07-01"), "154001", "A000200214352");
        Comprobante compVtaFac2 = new ComprobanteVtaFac(Date.valueOf("2016-07-01"), "202001", "A000105674712");
        Comprobante compVtaFac3 = new ComprobanteVtaFac(Date.valueOf("2016-07-05"), "402101", "A000300124891");

            //ITEMS FACTURAS DE VENTA
        ItemComprobante item5 = new ItemComprobante(art1, 8, 60);
        ItemComprobante item6 = new ItemComprobante(art1, 2, 62);
        ItemComprobante item7 = new ItemComprobante(art1, 3, 62);

        compVtaFac1.getItems().add(item5);
        compVtaFac2.getItems().add(item6);
        compVtaFac3.getItems().add(item7);

        comprobantes.add(compVtaFac1);
        comprobantes.add(compVtaFac2);
        comprobantes.add(compVtaFac3);

        //CREACION NOTAS DE CREDITO DE VENTA
        Comprobante compVtaCre1 = new ComprobanteVtaCre(Date.valueOf("2016-07-01"), "154001", "A000500214352");

            //ITEMS NOTAS DE CREDITO DE VENTA
        ItemComprobante item8 = new ItemComprobante(art1, 5, 62);

        compVtaCre1.getItems().add(item8);

        comprobantes.add(compVtaCre1);

        //CREACION NOTAS DE CREDITO DE COMPRA
        Comprobante compCpaCre1 = new ComprobanteCpaCre(Date.valueOf("2016-07-01"), "154001", "A000500214352");

            //ITEMS NOTAS DE CREDITO DE COMPRA
        ItemComprobante item9 = new ItemComprobante(art1, 2, 62);

        compCpaCre1.getItems().add(item9);

        comprobantes.add(compCpaCre1);

        //ORDENAR COMPROBANTES POR FECHA
        comprobantes.sort(new Comparator<Comprobante>() {
            public int compare(Comprobante o1, Comprobante o2) {
                return o1.getFecha().compareTo(o2.getFecha());
            }
        });
    }

    private void actualizarStock() {
        for (Comprobante comp: comprobantes) {
            comp.updateStock();
        }
    }

    private void mostrarDatos() {
        System.out.println("Articulos:");

        for (Articulo art: articulos) {
            System.out.println(art.toString());
        }

        System.out.println("Comprobantes:");

        for (Comprobante comp: comprobantes) {
            System.out.println("  " + comp.toString());

            for (ItemComprobante item: comp.getItems()) {
                System.out.println("    " + item.toString());
            }
        }
    }

    private void mostrarStock() {
        System.out.println("Stock:");

        for (Articulo art: articulos) {
            System.out.println("  " + art.toString());

            for (ItemStock item: art.getStock().getItems()) {
                System.out.println("    " + item.toString());
            }
        }
    }
}
