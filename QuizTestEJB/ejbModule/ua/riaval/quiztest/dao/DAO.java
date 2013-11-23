package ua.riaval.quiztest.dao;

import java.util.List;

import ua.riaval.quiztest.dao.implementation.OrderBy;

public interface DAO<T> {

	public T save(T entity);

	public void merge(T entity);

	public void delete(T entity);

	public List<T> findAll(OrderBy orderBy);
	
	public List<T> loadPart(int firstIndex, int amount, OrderBy orderBy);

	public T findByID(int id);
	
	public int count();

}
