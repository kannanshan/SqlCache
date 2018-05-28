package com.indix.cache.common;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static Configuration configuration;
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry standardServiceRegistry;
	static {
		try {
			String rootDirectory = null;
			configuration = new Configuration().configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
			sb.applySettings(configuration.getProperties());
			standardServiceRegistry = sb.build();
			sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
