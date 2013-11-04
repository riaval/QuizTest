package ua.riaval.quiztest.dao.implementation;

import javax.persistence.TypedQuery;

import ua.riaval.quiztest.entity.User;

public class UserDAOImpl extends DAOImpl<User> {

	public UserDAOImpl() {
		super(User.class);
	}

	public User findByEmail(String email) {
		TypedQuery<User> query = getEntityManager().createNamedQuery(
				"User.findByEmail", User.class);
		query.setParameter("email", email);

		return findOne(query);
		
	}
}
