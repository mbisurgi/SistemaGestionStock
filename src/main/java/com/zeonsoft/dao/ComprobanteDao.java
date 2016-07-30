package com.zeonsoft.dao;

import com.zeonsoft.model.Comprobante;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ComprobanteDao {
    private static ComprobanteDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ComprobanteDao() {
        sessionFactory = HibernateUtilSistema.getSessionFactory();
    }

    public static ComprobanteDao getInstancia() {
        if (instancia == null) {
            instancia = new ComprobanteDao();
        }

        return instancia;
    }


    public List<Comprobante> getComprobantes() {
        List<Comprobante> comprobantes = new ArrayList<Comprobante>();

        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Comprobante");

        comprobantes = query.list();

        return comprobantes;
    }
}
