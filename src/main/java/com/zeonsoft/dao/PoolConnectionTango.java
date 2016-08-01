package com.zeonsoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoolConnectionTango {
    private List<Connection> connections = new ArrayList<Connection>();

    private static PoolConnectionTango instancia = null;

    private PoolConnectionTango() {
        for (int i = 0; i < 5; i++) {
            connections.add(connect());
        }
    }

    public static PoolConnectionTango getInstancia() {
        if (instancia == null) {
            instancia = new PoolConnectionTango();
        }

        return instancia;
    }

    private Connection connect() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:jtds:sqlserver://BC-GROUP-SERVER/database=Galias_SRL", "sa_ale74", "Epec9854");

            return con;
        } catch (SQLException exSql) {
            System.out.println("Error: " + exSql.getMessage());
            System.out.println("Stack Trace: " + exSql.getStackTrace());
            return null;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Stack Trace: " + ex.getStackTrace());
            return null;
        }
    }

    public Connection getConnection() {
        Connection con = null;

        if (connections.size() > 0) {
            con = connections.remove(0);
        }
        else {
            con = connect();
        }

        return con;
    }

    public void releaseConnection(Connection con) {
        connections.add(con);
    }
}
