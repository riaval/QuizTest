package ua.riaval.quiztest.dao;

import java.util.List;

public interface DAO<T> {

	public T save(T entity);

	public void merge(T entity);

	public void delete(T entity);

	public List<T> findAll();

	public T findByID(int id);

}
