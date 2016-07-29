package com.zeonsoft.dao;

import com.zeonsoft.entities.tango.ComprobanteTangoCpa;
import com.zeonsoft.entities.tango.ComprobanteTangoVta;
import com.zeonsoft.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteTangoDao {
    private static ComprobanteTangoDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ComprobanteTangoDao() {
        sessionFactory = HibernateUtilTango.getSessionFactory();
    }

    public static ComprobanteTangoDao getInstancia() {
        if (instancia == null) {
            instancia = new ComprobanteTangoDao();
        }

        return instancia;
    }

    public List<Comprobante> getComprobantesTango() {
        List<Comprobante> comprobantes = new ArrayList<Comprobante>();

        Session session = sessionFactory.openSession();
        Query queryCompras = session.createQuery("from ComprobanteTangoCpa");
        Query queryVentas = session.createQuery("from ComprobanteTangoVta");

        List<ComprobanteTangoCpa> tangoCompras = queryCompras.list();
        List<ComprobanteTangoVta> tangoVentas = queryVentas.list();

        for (ComprobanteTangoCpa tangoCpa: tangoCompras) {
            Comprobante comp = null;

            if (tangoCpa.getT_comp().equals("FAC")) {
                 comp = new ComprobanteCpaFac(tangoCpa.getFecha_emis(), tangoCpa.getCod_provee(), tangoCpa.getN_comp());
            }

            if (tangoCpa.getT_comp().equals("N/C")) {
                comp = new ComprobanteCpaCre(tangoCpa.getFecha_emis(), tangoCpa.getCod_provee(), tangoCpa.getN_comp());
            }

            comprobantes.add(comp);
        }

        for (ComprobanteTangoVta tangoVta: tangoVentas) {
            Comprobante comp = null;

            if (tangoVta.getT_comp().equals("FAC")) {
                comp = new ComprobanteVtaFac(tangoVta.getFecha_emis(), tangoVta.getCod_client(), tangoVta.getN_comp());
            }

            if (tangoVta.getT_comp().equals("NCR")) {
                comp = new ComprobanteVtaCre(tangoVta.getFecha_emis(), tangoVta.getCod_client(), tangoVta.getN_comp());
            }

            comprobantes.add(comp);
        }

        session.close();

        return comprobantes;
    }
}
