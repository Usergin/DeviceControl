package com.shiz.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by oldman on 07.04.17.
 */

public class HibernateSessionFactory {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
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

    /**
     * Get current Hibernate session or initialize it if it's null
     *
     * @return Hibernate session instance
     */

    static void shutdown() {
        // Close caches and connection pools
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
