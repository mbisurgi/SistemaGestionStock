package com.zeonsoft.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoolConnectionSistema {
    private List<Connection> connections = new ArrayList<Connection>();

    private static PoolConnectionSistema instancia = null;

    private PoolConnectionSistema() {
        for (int i = 0; i < 5; i++) {
            connections.add(connect());
        }
    }

    public static PoolConnectionSistema getInstancia() {
        if (instancia == null) {
            instancia = new PoolConnectionSistema();
        }

        return instancia;
    }

    private Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemagestionstock", "root", "maximati");

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
