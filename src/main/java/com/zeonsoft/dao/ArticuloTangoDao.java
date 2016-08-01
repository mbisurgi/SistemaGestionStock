package com.zeonsoft.dao;

import com.zeonsoft.model.Articulo;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticuloTangoDao {
    private static ArticuloTangoDao instancia = null;
    private static SessionFactory sessionFactory = null;

    private ArticuloTangoDao() {
        //sessionFactory = HibernateUtilSistema.getSessionFactory();
    }

    public static ArticuloTangoDao getInstancia() {
        if (instancia == null) {
            instancia = new ArticuloTangoDao();
        }

        return instancia;
    }

    public List<Articulo> getArticulosTango() {
        List<Articulo> articulos = new ArrayList<Articulo>();

        Connection con = PoolConnectionTango.getInstancia().getConnection();

        try {
            String sql = "Select COD_ARTICU, DESCRIPCIO From STA11";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Articulo art = new Articulo(rs.getString("COD_ARTICU"), rs.getString("DESCRIPCIO"));

                articulos.add(art);
            }
        } catch (SQLException ex) {

        } finally {
            PoolConnectionTango.getInstancia().releaseConnection(con);
        }

        return articulos;
    }
}
