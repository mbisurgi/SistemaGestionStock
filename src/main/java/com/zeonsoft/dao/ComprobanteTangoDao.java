package com.zeonsoft.dao;

import com.zeonsoft.model.*;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteTangoDao {
    private static ComprobanteTangoDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ComprobanteTangoDao() {
        //sessionFactory = HibernateUtilTango.getSessionFactory();
    }

    public static ComprobanteTangoDao getInstancia() {
        if (instancia == null) {
            instancia = new ComprobanteTangoDao();
        }

        return instancia;
    }

    public List<Comprobante> getComprobantesTango(Date desde, Date hasta) {
        List<Comprobante> comprobantes = new ArrayList<Comprobante>();

        /*List<ComprobanteTangoCpa> tangoCompras = new ArrayList<ComprobanteTangoCpa>();
        List<ComprobanteTangoVta> tangoVentas = new ArrayList<ComprobanteTangoVta>();*/

        Connection con = PoolConnectionTango.getInstancia().getConnection();

        try {
            String sql;

            PreparedStatement ps;

            ResultSet rs;

            sql = "Select NCOMP_IN_C, T_COMP, N_COMP, FECHA_EMIS, COD_PROVEE From CPA04 Where COD_PROVEE = ? And FECHA_EMIS >= ? And FECHA_EMIS <= ?";

            ps = con.prepareStatement(sql);
            ps.setString(1, "100001");
            ps.setDate(2, desde);
            ps.setDate(3, hasta);

            rs = ps.executeQuery();

            while (rs.next()) {
                Comprobante comp = null;

                if (rs.getString("T_COMP").equals("FAC")) {
                    comp = new ComprobanteCpaFac(rs.getDate("FECHA_EMIS"), rs.getString("COD_PROVEE"), rs.getString("N_COMP"));
                    comp.setItems(getItems("CPA", "FAC", rs.getString("NCOMP_IN_C")));
                }

                if (rs.getString("T_COMP").equals("N/C")) {
                    comp = new ComprobanteCpaCre(rs.getDate("FECHA_EMIS"), rs.getString("COD_PROVEE"), rs.getString("N_COMP"));
                    comp.setItems(getItems("CPA", "N/C", rs.getString("NCOMP_IN_C")));
                }

                if (comp != null) {
                    comprobantes.add(comp);
                }
            }

            sql = "Select T_COMP, N_COMP, FECHA_EMIS, COD_CLIENT From GVA12 Where FECHA_EMIS >= ? And FECHA_EMIS <= ?";
            ps = con.prepareStatement(sql);
            ps.setDate(1, desde);
            ps.setDate(2, hasta);

            rs = ps.executeQuery();

            while (rs.next()) {
                Comprobante comp = null;

                if (rs.getString("T_COMP").equals("FAC")) {
                    comp = new ComprobanteVtaFac(rs.getDate("FECHA_EMIS"), rs.getString("COD_CLIENT"), rs.getString("N_COMP"));
                    comp.setItems(getItems("VTA", "FAC", rs.getString("N_COMP")));
                }

                if (rs.getString("T_COMP").equals("NCR") || rs.getString("T_COMP").equals("N/C")) {
                    comp = new ComprobanteVtaCre(rs.getDate("FECHA_EMIS"), rs.getString("COD_CLIENT"), rs.getString("N_COMP"));
                    comp.setItems(getItems("VTA", "NCR", rs.getString("N_COMP")));
                }

                if (comp != null) {
                    comprobantes.add(comp);
                }
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnectionTango.getInstancia().releaseConnection(con);
        }

        /*for (ComprobanteTangoCpa tangoCpa: tangoCompras) {
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
        }*/

        return comprobantes;
    }

    public List<ItemComprobante> getItems(String tipo, String comp, String nroComprobante) {
        List<ItemComprobante> items = new ArrayList<ItemComprobante>();

        Connection con = PoolConnectionTango.getInstancia().getConnection();

        try {
            String sql;

            PreparedStatement ps = null;

            ResultSet rs;

            if (tipo.equals("CPA")) {
                sql = "Select COD_ARTICU, CANTIDAD, PRECIO_NET From CPA46 Where NCOMP_IN_C = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, nroComprobante);
            }

            if (tipo.equals("VTA")) {
                sql = "Select COD_ARTICU, CANTIDAD, PRECIO_NET From GVA53 Where T_COMP = ? And N_COMP = ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, comp);
                ps.setString(2, nroComprobante);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                ItemComprobante item = new ItemComprobante();

                item.setArticulo(ArticuloDao.getInstancia().getArticulo(rs.getString("COD_ARTICU")));
                item.setCantidad(rs.getInt("CANTIDAD"));
                item.setPrecio(rs.getFloat("PRECIO_NET"));

                items.add(item);
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnectionTango.getInstancia().releaseConnection(con);
        }

        return items;
    }
}
