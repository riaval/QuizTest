package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.UserDAO;
import ua.riaval.quiztest.entity.User;

@Stateless
public class UserDAOImpl extends DAOImpl<User> implements UserDAO {

	public UserDAOImpl() {
		super(User.class);
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail",
				User.class);
		query.setParameter("email", email);

		return findOne(query);
	}
}
