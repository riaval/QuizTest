package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.User;

@Local
public interface UserDAO extends DAO<User> {

	public User findByEmail(String email);
	
}
