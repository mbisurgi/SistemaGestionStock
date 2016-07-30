package com.zeonsoft.controller;

import com.zeonsoft.dao.ComprobanteDao;
import com.zeonsoft.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SistemaGestionStock {
    private static SistemaGestionStock instancia = null;

    private List<Articulo> articulos;
    private List<Comprobante> comprobantes;

    private SistemaGestionStock() {
        articulos = new ArrayList<Articulo>();
        comprobantes = new ArrayList<Comprobante>();

        init();
    }

    public static SistemaGestionStock getInstancia() {
        if (instancia == null) {
            instancia = new SistemaGestionStock();
        }

        return instancia;
    }

    private void init() {
        Articulo art1 = new Articulo("122015066", "LP EXT MALTA DIASTASICO 5KG");

        articulos.add(art1);

        Comprobante cpa9999 = new ComprobanteCpaFac(Date.valueOf("2016-05-31"), "100001", "A999999999999");
        ItemComprobante cpa999_1 = new ItemComprobante(art1, 10, 158.70f);
        cpa9999.getItems().add(cpa999_1);

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

        comprobantes.add(cpa9999);
        comprobantes.add(vta0967);
        comprobantes.add(vta1005);
        comprobantes.add(vta1163);
        comprobantes.add(vta1173);
        comprobantes.add(cpa1837);
    }

    public void procesarComprobantes() {
        comprobantes.sort(new Comparator<Comprobante>() {
            public int compare(Comprobante o1, Comprobante o2) {
                return o1.getFecha().compareTo(o2.getFecha());
            }
        });

        for (Comprobante comp: comprobantes) {
            comp.updateStock();
        }
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }
}
