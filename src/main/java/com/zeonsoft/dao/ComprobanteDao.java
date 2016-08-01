package com.zeonsoft.dao;

import com.zeonsoft.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteDao {
    private static ComprobanteDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ComprobanteDao() {
        //sessionFactory = HibernateUtilSistema.getSessionFactory();
    }

    public static ComprobanteDao getInstancia() {
        if (instancia == null) {
            instancia = new ComprobanteDao();
        }

        return instancia;
    }

    public void insertComprobante(Comprobante comp) {
        Connection con = PoolConnection.getInstancia().getConnection();

        try {
            String sql = "Insert Into comprobantes (tipo, nroComprobante, fecha, entidad) Values (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            if (comp instanceof ComprobanteCpaFac) {
                ps.setString(1, "CPAFAC");
            }

            if (comp instanceof ComprobanteCpaCre) {
                ps.setString(1, "CPACRE");
            }

            if (comp instanceof ComprobanteVtaFac) {
                ps.setString(1, "VTAFAC");
            }

            if (comp instanceof ComprobanteVtaCre) {
                ps.setString(1, "VTACRE");
            }

            ps.setString(2, comp.getNroComprobante());
            ps.setDate(3, comp.getFecha());
            ps.setString(4, comp.getEntidad());

            ps.executeUpdate();

        } catch (SQLException ex) {

        } finally {
            PoolConnection.getInstancia().releaseConnection(con);
        }
    }

    public List<Comprobante> getComprobantes() {
        List<Comprobante> comprobantes = new ArrayList<Comprobante>();

        Connection con = PoolConnection.getInstancia().getConnection();

        try {
            String sql = "Select * From comprobantes";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Comprobante comp = null;

                if (rs.getString("tipo").equals("CPAFAC")) {
                    comp = new ComprobanteCpaFac(rs.getDate("fecha"), rs.getString("entidad"), rs.getString("nroComprobante"));
                    comp.setItems(getItems("CPAFAC", rs.getString("nroComprobante")));
                }

                if (rs.getString("tipo").equals("CPACRE")) {
                    comp = new ComprobanteCpaCre(rs.getDate("fecha"), rs.getString("entidad"), rs.getString("nroComprobante"));
                    comp.setItems(getItems("CPACRE", rs.getString("nroComprobante")));
                }

                if (rs.getString("tipo").equals("VTAFAC")) {
                    comp = new ComprobanteVtaFac(rs.getDate("fecha"), rs.getString("entidad"), rs.getString("nroComprobante"));
                    comp.setItems(getItems("VTAFAC", rs.getString("nroComprobante")));
                }

                if (rs.getString("tipo").equals("VTACRE")) {
                    comp = new ComprobanteVtaCre(rs.getDate("fecha"), rs.getString("entidad"), rs.getString("nroComprobante"));
                    comp.setItems(getItems("VTACRE", rs.getString("nroComprobante")));
                }

                comprobantes.add(comp);
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnection.getInstancia().releaseConnection(con);
        }

        return comprobantes;
    }

    public List<ItemComprobante> getItems(String tipo, String nroComprobante) {
        List<ItemComprobante> items = new ArrayList<ItemComprobante>();

        Connection con = PoolConnection.getInstancia().getConnection();

        try {
            String sql = "Select * From itemscomprobante Where tipo = ? and nroComprobante = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            ps.setString(2, nroComprobante);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemComprobante item = new ItemComprobante(ArticuloDao.getInstancia().getArticulo(rs.getString("nroArticulo")), rs.getInt("cantidad"), rs.getFloat("precio"));
                item.setIdItem(rs.getInt("id"));

                items.add(item);
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnection.getInstancia().releaseConnection(con);
        }

        return items;
    }
}
