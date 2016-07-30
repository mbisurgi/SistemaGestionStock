package com.zeonsoft.dao;

import com.zeonsoft.entities.tango.ComprobanteTangoCpa;
import com.zeonsoft.entities.tango.ComprobanteTangoVta;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtilSistema {
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() throws HibernateException {
        Configuration config = new Configuration();
        config.setProperties(getHibernateProperties());
        addAnnotatedClasses(config);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                applySettings(config.getProperties());
        SessionFactory factory = config.buildSessionFactory(builder.build());
        return factory;
    }

    private static void addAnnotatedClasses(Configuration config) {
        //config.addAnnotatedClass(ComprobanteTangoCpa.class);
        //config.addAnnotatedClass(ComprobanteTangoVta.class);
    }

    private static Properties getHibernateProperties() {
        Properties props = new Properties();
        // Establece el driver de conexion dependiente del RDBMS
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        // Establece la url de conexion dependiente del RDBMS
        props.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/hibernate");
        // Establece el usuario
        props.put("hibernate.connection.username", "administrador");
        // Establece la clave
        props.put("hibernate.connection.password", "maximati");
        // Establece el dialecto a utilizar
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // Establece el uso de logging, deberá existir el archivo log4j.properties
        props.put("hibernate.show_sql", "true");
        return props;
    }
}
