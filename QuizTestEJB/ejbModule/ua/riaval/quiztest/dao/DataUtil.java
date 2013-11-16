package ua.riaval.quiztest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataUtil {

	private static final EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static int amountOfCalls;

	private static final int AMOUNT_OF_CALLS_LIMIT = 50;

	static {
		try {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("QuizTest");
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Throwable e) {
			System.err.println("Initial EntityManager creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static EntityManagerFactory getEntityManagerFactoryFactory() {
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
//		if (amountOfCalls > AMOUNT_OF_CALLS_LIMIT) {
//			entityManager.flush();
//			entityManager.clear();
//		}
//		amountOfCalls++;

		return entityManager;
	}

//	public static void safeCommit() {
//		try {
//			getEntityManager().getTransaction().commit();
//		} finally {
//			if (entityManager.getTransaction().isActive()) {
//				rollback();
//			}
//		}
//	}
	
	public static void begin() {
		getEntityManager().getTransaction().begin();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}
	
	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static void closeEntityManager() {
		getEntityManager().close();
	}
	
	public static boolean isActive() {
		return getEntityManager().getTransaction().isActive();
	}

}
