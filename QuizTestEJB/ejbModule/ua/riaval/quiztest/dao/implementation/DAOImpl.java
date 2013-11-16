package ua.riaval.quiztest.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.DAO;

public abstract class DAOImpl<T> implements DAO<T> {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	protected EntityManager em;

	public DAOImpl(Class<T> clazz) {
		persistClass = clazz;

		StringBuilder output = new StringBuilder();
		output.append("SELECT e FROM ").append(persistClass.getSimpleName())
				.append(" AS e").append(" ORDER BY e.id DESC");
		findAllQuery = output.toString();
	}

	@Override
	public T save(T entity) {
		em.persist(entity);

		return entity;
	}

	@Override
	public void merge(T entity) {
		em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(entity);
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> query = em.createQuery(findAllQuery, persistClass);

		return findMany(query);
	}

	@Override
	public T findByID(int id) {
		T entity = em.find(persistClass, id);

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

	private Class<T> persistClass;
	private String findAllQuery;

}
