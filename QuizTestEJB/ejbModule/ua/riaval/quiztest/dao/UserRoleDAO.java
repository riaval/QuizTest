package ua.riaval.quiztest.dao;

import javax.ejb.Local;

import ua.riaval.quiztest.entity.UserRole;

@Local
public interface UserRoleDAO extends DAO<UserRole> {

	public UserRole findByRole(String role);

}
