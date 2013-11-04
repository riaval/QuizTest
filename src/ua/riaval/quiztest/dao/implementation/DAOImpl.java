package ua.riaval.quiztest.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.DAO;
import ua.riaval.quiztest.dao.DataUtil;

public abstract class DAOImpl<T> implements DAO<T> {

	public DAOImpl(Class<T> clazz) {
		persistClass = clazz;

		StringBuilder output = new StringBuilder();
		output.append("SELECT e FROM ").append(persistClass.getSimpleName())
				.append(" AS e");
		findAllQuery = output.toString();
	}

	@Override
	public T save(T entity) {
		getEntityManager().persist(entity);

		return entity;
	}

	@Override
	public void merge(T entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> query = getEntityManager().createQuery(findAllQuery,
				persistClass);

		return findMany(query);
	}

	@Override
	public T findByID(long id) {
		T entity = getEntityManager().find(persistClass, id);

		return entity;
	}

	protected T findOne(TypedQuery<T> query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	protected List<T> findMany(TypedQuery<T> query) {
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	protected EntityManager getEntityManager() {
		return DataUtil.getEntityManager();
	}

	private Class<T> persistClass;
	private String findAllQuery;

}
