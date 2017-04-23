package com.shiz.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by oldman on 07.04.17.
 */

public class HibernateSessionFactory {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create registry

//                Configuration cnf = new Configuration();
//
//                // Properties
//                cnf.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
//                cnf.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb");
//                cnf.setProperty("hibernate.connection.username", "root");
//                cnf.setProperty("hibernate.connection.password", "root");
//                cnf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//                cnf.setProperty("hibernate.hbm2ddl.auto", "update");
//                cnf.setProperty("hibernate.show_sql", "true");
//                cnf.setProperty(" hibernate.connection.pool_size", "1");

                registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .build();
                MetadataSources sources = new MetadataSources(registry);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    static void shutdown() {
        // Close caches and connection pools
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
