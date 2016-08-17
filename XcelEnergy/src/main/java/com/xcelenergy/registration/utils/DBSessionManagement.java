package com.xcelenergy.registration.utils;

import java.sql.Connection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBSessionManagement {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure("/spring/database/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Connection getConnectionDS() {

		Connection conn = null;
		javax.sql.DataSource ds = null;
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		env.put(Context.PROVIDER_URL, "t3://localhost:7001");

		try {
			Context context = new InitialContext(env);
			ds = (javax.sql.DataSource) context.lookup("BooksDs");
			conn = ds.getConnection();
			System.out.println("Connection: " + conn);
			return conn;
		} catch (Exception ex) {
			// handle the exception
			ex.printStackTrace();
		}
		return conn;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

}
