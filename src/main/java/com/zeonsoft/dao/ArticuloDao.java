package com.zeonsoft.dao;

import com.zeonsoft.model.Articulo;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDao {
    private static ArticuloDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ArticuloDao() {
        //sessionFactory = HibernateUtilSistema.getSessionFactory();
    }

    public static ArticuloDao getInstancia() {
        if (instancia == null) {
            instancia = new ArticuloDao();
        }

        return instancia;
    }

    public void insert(Articulo art) {
        Connection con = PoolConnectionSistema.getInstancia().getConnection();

        try {
            String sql = "Insert Into articulos (nroArticulo, nombreArticulo) Values (?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, art.getNroArticulo());
            ps.setString(2, art.getNombreArticulo());

            ps.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            PoolConnectionSistema.getInstancia().releaseConnection(con);
        }
    }

    public List<Articulo> getArticulos() {
        List<Articulo> articulos = new ArrayList<Articulo>();

        Connection con = PoolConnectionSistema.getInstancia().getConnection();

        try {
            String sql = "Select * From articulos";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Articulo art = new Articulo(rs.getString("nroArticulo"), rs.getString("nombreArticulo"));

                articulos.add(art);
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnectionSistema.getInstancia().releaseConnection(con);
        }

        return articulos;
    }

    public Articulo getArticulo(String nroArticulo) {
        Articulo art = null;

        Connection con = PoolConnectionSistema.getInstancia().getConnection();

        try {
            String sql = "Select * From articulos Where nroArticulo = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nroArticulo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                art = new Articulo(rs.getString("nroArticulo"), rs.getString("nombreArticulo"));
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnectionSistema.getInstancia().releaseConnection(con);
        }

        return art;
    }
}
