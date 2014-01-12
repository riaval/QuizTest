package ua.riaval.quiztest.dao.implementation;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import ua.riaval.quiztest.dao.UserRoleDAO;
import ua.riaval.quiztest.entity.UserRole;

@Stateless
public class UserRoleDAOImpl extends DAOImpl<UserRole> implements UserRoleDAO {

	public UserRoleDAOImpl() {
		super(UserRole.class);
	}

	@Override
	public UserRole findByRole(String role) {
		TypedQuery<UserRole> query = em.createNamedQuery("UserRole.findByRole",
				UserRole.class);
		query.setParameter("role", role);

		return findOne(query);
	}

}
