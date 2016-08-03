package com.zeonsoft.controller;

import com.zeonsoft.dao.ArticuloDao;
import com.zeonsoft.dao.ArticuloTangoDao;
import com.zeonsoft.dao.ComprobanteDao;
import com.zeonsoft.dao.ComprobanteTangoDao;
import com.zeonsoft.model.*;

import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SistemaGestionStock {
    private static SistemaGestionStock instancia = null;

    private List<Articulo> articulos;
    private List<Comprobante> comprobantes;

    private SistemaGestionStock() {
        articulos = ArticuloDao.getInstancia().getArticulos();
        comprobantes = ComprobanteDao.getInstancia().getComprobantes();

        //init();
    }

    public static SistemaGestionStock getInstancia() {
        if (instancia == null) {
            instancia = new SistemaGestionStock();
        }

        return instancia;
    }

    private void init() {
        Articulo art1 = buscarArticulo("122015066");
        Articulo art2 = buscarArticulo("132322093");

        //articulos.add(art1);
        //articulos.add(art2);

        //MOVIMIENTOS SALDO INICIAL
        Comprobante cpa9999 = new ComprobanteCpaFac(Date.valueOf("2016-05-31"), "100001", "A999999999999");
        ItemComprobante cpa999_1 = new ItemComprobante(art1, 11, 158.70f);
        ItemComprobante cpa999_2 = new ItemComprobante(art2, 144, 433.88f);
        cpa9999.getItems().add(cpa999_1);
        cpa9999.getItems().add(cpa999_2);

        comprobantes.add(cpa9999);

        //MOVIMIENTOS DE JUNIO ART 122015055
        Comprobante vta0967 = new ComprobanteVtaFac(Date.valueOf("2016-06-03"), "100007", "A000100000967");
        ItemComprobante vta0967_1 = new ItemComprobante(art1, 1, 219.88f);
        vta0967.getItems().add(vta0967_1);

        Comprobante vta1005 = new ComprobanteVtaFac(Date.valueOf("2016-06-09"), "100005", "A000100001005");
        ItemComprobante vta1005_1 = new ItemComprobante(art1, 2, 219.88f);
        vta1005.getItems().add(vta1005_1);

        Comprobante vta1163 = new ComprobanteVtaFac(Date.valueOf("2016-06-30"), "100005", "A000100001163");
        ItemComprobante vta1163_1 = new ItemComprobante(art1, 2, 219.88f);
        vta1163.getItems().add(vta1163_1);

        Comprobante vta1173 = new ComprobanteVtaFac(Date.valueOf("2016-06-30"), "100018", "A000100001173");
        ItemComprobante vta1173_1 = new ItemComprobante(art1, 1, 219.88f);
        vta1173.getItems().add(vta1173_1);

        Comprobante cpa1837 = new ComprobanteCpaFac(Date.valueOf("2016-06-24"), "100001", "A004700031837");
        ItemComprobante cpa1837_1 = new ItemComprobante(art1, 5, 158.70f);
        cpa1837.getItems().add(cpa1837_1);

        comprobantes.add(vta0967);
        comprobantes.add(vta1005);
        comprobantes.add(vta1163);
        comprobantes.add(vta1173);
        comprobantes.add(cpa1837);

        //MOVIMIENTOS DE JUNIO ART 132322093
        Comprobante vta1105 = new ComprobanteVtaFac(Date.valueOf("2016-06-23"), "100088", "A000100001105");
        ItemComprobante vta1105_1 = new ItemComprobante(art2, 50, 542.52f);
        ItemComprobante vta1105_2 = new ItemComprobante(art2, 20, 0f);
        vta1105.getItems().add(vta1105_1);
        vta1105.getItems().add(vta1105_2);

        Comprobante vta1127 = new ComprobanteVtaFac(Date.valueOf("2016-06-24"), "100074", "A000100001127");
        ItemComprobante vta1127_1 = new ItemComprobante(art2, 10, 0f);
        vta1127.getItems().add(vta1127_1);

        Comprobante cpa1590 = new ComprobanteCpaFac(Date.valueOf("2016-06-01"), "100001", "A004700031590");
        ItemComprobante cpa1590_1 = new ItemComprobante(art2, 480, 433.88f);
        ItemComprobante cpa1590_2 = new ItemComprobante(art2, 8, 433.88f);
        cpa1590.getItems().add(cpa1590_1);
        cpa1590.getItems().add(cpa1590_2);

        Comprobante cpa1621 = new ComprobanteCpaFac(Date.valueOf("2016-06-01"), "100001", "A004700031621");
        ItemComprobante cpa1621_1 = new ItemComprobante(art2, 92, 433.88f);
        cpa1621.getItems().add(cpa1621_1);

        Comprobante cpa1909 = new ComprobanteCpaFac(Date.valueOf("2016-06-29"), "100001", "A004700031909");
        ItemComprobante cpa1909_1 = new ItemComprobante(art2, 208, 386.16f);
        cpa1909.getItems().add(cpa1909_1);

        comprobantes.add(vta1105);
        comprobantes.add(vta1127);
        comprobantes.add(cpa1590);
        comprobantes.add(cpa1621);
        comprobantes.add(cpa1909);
    }

    public void sincronizarArticulos() {
        Map<String, Articulo> articulosSync = new HashMap<String, Articulo>();
        List<Articulo> articulosTango = ArticuloTangoDao.getInstancia().getArticulosTango();

        for (Articulo art: this.articulos) {
            articulosSync.put(art.getNroArticulo(), art);
        }

        for (Articulo art: articulosTango) {
            if (!articulosSync.containsKey(art.getNroArticulo())) {
                ArticuloDao.getInstancia().insert(art);
                this.articulos.add(art);
            }
        }

        System.out.println("Proceso Finalizado Correctamente");
    }

    public void sincronizarComprobantes(Date desde, Date hasta) {
        Map<String, Comprobante> comprobantesSync = new HashMap<String, Comprobante>();
        List<Comprobante> comprobantesTango = ComprobanteTangoDao.getInstancia().getComprobantesTango(desde, hasta);

        for (Comprobante comp: this.comprobantes) {
            if (comp instanceof ComprobanteCpaFac) {
                comprobantesSync.put("CPAFAC" + comp.getNroComprobante(), comp);
            }

            if (comp instanceof ComprobanteCpaCre) {
                comprobantesSync.put("CPACRE" + comp.getNroComprobante(), comp);
            }

            if (comp instanceof ComprobanteVtaFac) {
                comprobantesSync.put("VTAFAC" + comp.getNroComprobante(), comp);
            }

            if (comp instanceof ComprobanteVtaCre) {
                comprobantesSync.put("VTACRE" + comp.getNroComprobante(), comp);
            }
        }

        for (Comprobante comp: comprobantesTango) {
            String tipo = "";

            if (comp instanceof ComprobanteCpaFac) {
                tipo = "CPAFAC";
            }

            if (comp instanceof ComprobanteCpaCre) {
                tipo = "CPACRE";
            }

            if (comp instanceof ComprobanteVtaFac) {
                tipo = "VTAFAC";
            }

            if (comp instanceof ComprobanteVtaCre) {
                tipo = "VTACRE";
            }

            if (!comprobantesSync.containsKey(tipo + comp.getNroComprobante())) {
                ComprobanteDao.getInstancia().insertComprobante(comp);
                this.comprobantes.add(comp);
            }
        }

        System.out.println("Proceso Finalizado Correctamente");
    }

    public void procesarComprobantes() {
        comprobantes.sort((o1, o2) -> o1.getFecha().compareTo(o2.getFecha()));

//        List<Comprobante> filtrado = new ArrayList<>();
//
//        for (Comprobante comp: comprobantes) {
//            List<ItemComprobante> items = comp.getItems().stream().filter(i -> i.getArticulo().getNroArticulo().equals("140021015")).collect(Collectors.toList());
//
//            comp.setItems(items);
//
//            if (comp.getItems().size() != 0) {
//                filtrado.add(comp);
//            }
//        }
//
//        for (Comprobante comp: filtrado) {
//            comp.updateStock();
//        }

        for (Comprobante comp: comprobantes) {
            comp.updateStock();
        }
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public int getStock(String nroArticulo, Date desde, Date hasta) {
        Articulo art = buscarArticulo(nroArticulo);

        int cantidad = 0;

        if (art != null) {
            cantidad = art.getStock().getStockUniFecha(desde, hasta);
        }

        return cantidad;
    }

    public float getMargen(String nroArticulo, Date desde, Date hasta) {
        Articulo art = buscarArticulo(nroArticulo);

        float margen = 0;

        if (art != null) {
            margen = art.getMargen().getMargen$Fecha(desde, hasta);
        }

        return margen;
    }

    public float getCmv(String nroArticulo, Date desde, Date hasta) {
        Articulo art = buscarArticulo(nroArticulo);

        float cmv = 0;

        if (art != null) {
            cmv = art.getMargen().getCmv$Fecha(desde, hasta);
        }

        return cmv;
    }

    public List<ArticuloView> getResumen(Date desde, Date hasta) {
        List<ArticuloView> listado = new ArrayList<ArticuloView>();

        for (Articulo art: articulos) {
            if (art.getStock().getItems().size() != 0 && art.getMargen().getItems().size() != 0) {
                listado.add(art.getArticuloView(desde, hasta));
            }
        }

        return listado;
    }

    public Articulo buscarArticulo(String nroArticulo) {
        for (Articulo art: articulos) {
            if (art.getNroArticulo().equals(nroArticulo)) {
                return art;
            }
        }

        return null;
    }
}
