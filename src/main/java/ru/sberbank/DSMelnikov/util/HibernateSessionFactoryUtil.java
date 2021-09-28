package ru.sberbank.DSMelnikov.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        return config.buildSessionFactory();
    }
}
